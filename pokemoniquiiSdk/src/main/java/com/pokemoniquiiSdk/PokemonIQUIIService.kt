package com.pokemoniquiiSdk


import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.*
import javax.net.ssl.*


open class PokemonIQUIIService<E> {


    enum class Type {
        SERVICES
    }

    fun getService(endPointClass: Class<E>): E {
        return getService(Type.SERVICES, endPointClass)
    }


    fun getService(type: Type, endPointClass: Class<E>): E {
        when (type) {
            else -> {
                if (retrofitForServices == null) {
                    retrofitForServices = getRetrofitInstance(Type.SERVICES)
                }
                return retrofitForServices!!.create(endPointClass)
            }
        }
    }

    @NonNull
    fun getRetrofitInstance(type: Type): Retrofit {

        var okHttpBuilder: OkHttpClient.Builder? = null
        okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor(AddConfigurationAndDeviceIdInterceptor(type))
            .addNetworkInterceptor(StethoInterceptor())

        return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder!!.build())
            .baseUrl(PokemonIQUIISdkAPI.configuration.getBaseUrl(type))
            .build()


    }

    private fun applySelfSignedCertificates(
        okHttpBuilder: OkHttpClient.Builder,
        certificates: List<String>
    ) {
        try {
            val trustManager: X509TrustManager
            val sslSocketFactory: SSLSocketFactory

            trustManager = trustManagerForCertificates(certificates)
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
            sslSocketFactory = sslContext.socketFactory

            /*
            okHttpBuilder.sslSocketFactory(sslSocketFactory)
            okHttpBuilder.hostnameVerifier { hostname, session -> true }

             */

        } catch (e: CertificateException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        } catch (e: IOException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        } catch (e: KeyManagementException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        } catch (e: UnrecoverableKeyException) {
            Log.e(TAG, "getRetrofitBuilder: ", e)
        }

    }


    /**
     * Returns a trust manager that trusts `certificates` and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a `SSLHandshakeException`.
     *
     * This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     *
     * See also [CertificatePinner], which can limit trusted certificates while still using
     * the host platform's built-in trust store.
     *
     *
     * <h3>Warning: Customizing Trusted Certificates is Dangerous!</h3>
     *
     * Relying on your own trusted certificates limits your server team's ability to update their
     * TLS certificates. By installing a specific set of trusted certificates, you take on additional
     * operational complexity and limit your ability to migrate between certificate authorities. Do
     * not use custom trusted certificates in production without the blessing of your server's TLS
     * administrator.
     */
    @Throws(
        IOException::class,
        CertificateException::class,
        NoSuchAlgorithmException::class,
        KeyStoreException::class,
        UnrecoverableKeyException::class
    )
    private fun trustManagerForCertificates(certificatesStringList: List<String>): X509TrustManager {
        val certificates = getCertificates(
            certificatesStringList,
            CertificateFactory.getInstance("X.509")
        )

        // Put the certificates a key store.
        val password = "password".toCharArray() // Any password will work.
        val keyStore = newEmptyKeyStore(password)
        var index = 0
        for (certificate in certificates) {
            val certificateAlias = Integer.toString(index++)
            keyStore.setCertificateEntry(certificateAlias, certificate)
        }

        // Use it to build an X509 trust manager.
        val keyManagerFactory =
            KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password)
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException(
                "Unexpected default trust managers:" + Arrays.toString(
                    trustManagers
                )
            )
        }
        return trustManagers[0] as X509TrustManager
    }

    @Throws(KeyStoreException::class, CertificateException::class, NoSuchAlgorithmException::class)
    private fun newEmptyKeyStore(password: CharArray): KeyStore {
        try {
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            val `in`: InputStream? = null // By convention, 'null' creates an empty key store.
            keyStore.load(`in`, password)
            return keyStore
        } catch (e: IOException) {
            throw AssertionError(e)
        }

    }


    abstract inner class NetworkConnectionInterceptor : Interceptor {

        abstract val isInternetAvailable: Boolean

        abstract fun onInternetUnavailable()

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain!!.request()
            if (!isInternetAvailable) {
                onInternetUnavailable()
            }
            return chain.proceed(request)
        }
    }

    private class AddConfigurationAndDeviceIdInterceptor(var type: PokemonIQUIIService.Type) :
        Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            var original: Request = chain!!.request()
            var requestBuilder: Request.Builder = original.newBuilder()
                .method(original.method, original.body)

            var appName: String = PokemonIQUIISdkAPI.configuration.appName!!
            if (appName != null) {
                requestBuilder.header("app-name", appName)
                requestBuilder.header("Content-Type", "application/json; charset=utf-8")
            }

            var request: Request = requestBuilder.build()
            try {
                return chain.proceed(request)
            } catch (err: Exception) {
                return err.message?.let {
                    Response.Builder().message(it)
                        .protocol(Protocol.HTTP_2)
                        .request(request)
                        .code(400)
                        .body(object : ResponseBody() {
                            @Nullable
                            override fun contentType(): MediaType? {
                                return null
                            }

                            override fun contentLength(): Long {
                                return 0
                            }

                            override fun source(): BufferedSource {
                                return Buffer()
                            }
                        }).build()
                }!!
            }
        }
    }

    companion object {

        var TAG: String = PokemonIQUIIService::class.java.name
        var retrofitForServices: Retrofit? = null

        fun reset() {
            retrofitForServices = null
        }

        @Throws(CertificateException::class, IOException::class)
        private fun getCertificates(
            certificates: List<String>,
            certificateFactory: CertificateFactory
        ): Collection<Certificate> {
            val certificatesCollection: Collection<Certificate>
            val buffer = Buffer()
            var certificatesInputStream: InputStream? = null
            try {
                for (stringCertificate in certificates) {
                    buffer.writeUtf8(stringCertificate)
                }
                certificatesInputStream = buffer.inputStream()
                certificatesCollection =
                    certificateFactory.generateCertificates(certificatesInputStream)
            } finally {
                if (certificatesInputStream != null) {
                    certificatesInputStream.close()
                }
                buffer.close()
            }
            if (certificates.isEmpty()) {
                throw IllegalArgumentException("expected non-empty set of trusted certificates")
            }
            return certificatesCollection
        }
    }

}

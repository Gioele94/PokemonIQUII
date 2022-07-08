package com.pokemoniquiiSdk.common

import com.google.gson.Gson
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonReader
import com.pokemoniquiiSdk.common.exceptions.*
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.exceptions.CompositeException
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.StringReader


fun <T : Response> Single<T>.intercept(): Single<T> {
    return map {
        if (it == null) {
            throw NullResponseException()
        } else {
            if (it is Response && (it.error != -1 && it.error != 0)) {
                throw createExceptionFor2xxStatusCode(it)
            }
            if (it is ResponseItems<*> || it is ResponseItem<*>) {
                val itemResponseWithNullItem = it is ResponseItem<*> && it.item == null
                val itemsResponseWithNullItems = it is ResponseItems<*> && it.results == null
                if (itemResponseWithNullItem || itemsResponseWithNullItems) {
                    throw NullResponseException()
                }
            }
        }
        it
    }.onErrorResumeNext {
        if (it is HttpException) {
            return@onErrorResumeNext errorBuilder<T>(it) as SingleSource<out T>
        } else if (it is CompositeException && it.size() == 1 && it.exceptions[0] is HttpException) {
            return@onErrorResumeNext errorBuilder<T>(it.exceptions[0] as HttpException) as SingleSource<out T>
        } else {
            println("Single.error($it)")
            return@onErrorResumeNext Single.error(it)
        }
    }
}

private fun <T : Response> errorBuilder(it: HttpException): SingleSource<out T>? =
    when (it.code()) {
        in 300..399 -> {
            Single.error(
                createExceptionForNon2xxStatusCode(it,
                    ResponseErrorWith3xxException(it.message(), it).run {
                        this
                    })
            )
        }
        in 400..499 -> {
            Single.error(createExceptionForNon2xxStatusCode(
                it,
                ResponseErrorWith4xxException(it.message(), it)
            ).run {
                this
            })
        }
        in 500..599 -> {
            Single.error(
                createExceptionForNon2xxStatusCode(it,
                    ResponseErrorWith5xxException(it.message(), it).run {
                        this
                    })
            )
        }
        else -> {
            Single.error(it)
        }
    }


private fun createExceptionFor2xxStatusCode(it: Response): ResponseException {
    val exception = ResponseErrorWith2xxException()
    exception.pokemonErrorCode = it.error
    exception.pokemonErrorMessage = checkMessage(it)
    return exception
}

private fun createExceptionForNon2xxStatusCode(
    httpException: HttpException,
    responseException: ResponseException
): ResponseException {
    val rawResponse = httpException.response() ?: throw NullResponseException()

    responseException.statusCode = httpException.code()
    responseException.pokemonErrorMessage = httpException.message().toString()

    val bytes = rawResponse.errorBody()?.bytes()
    var errorJson = if (bytes != null && !bytes.isEmpty()) String(bytes) else ""

    if (errorJson.isNotEmpty() && isJSONValid(errorJson)) {
        val jsonReader = JsonReader(StringReader(errorJson))
        jsonReader.isLenient = true
        val response = Gson().fromJson(Streams.parse(jsonReader), Response::class.java)
        responseException.pokemonErrorCode = response.error
        responseException.pokemonErrorMessage = checkMessage(response)
    }

    return responseException
}

fun checkMessage(response: Response): String {
    if (response.message != null) {
        return response.message!!
    }

    return ""
}

fun isJSONValid(test: String): Boolean {
    try {
        JSONObject(test)
    } catch (ex: JSONException) {
        try {
            JSONArray(test)
        } catch (ex1: JSONException) {
            return false
        }

    }

    return true
}

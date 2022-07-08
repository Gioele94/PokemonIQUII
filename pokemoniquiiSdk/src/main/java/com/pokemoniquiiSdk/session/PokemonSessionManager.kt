package com.pokemoniquiiSdk.session

import android.annotation.SuppressLint
import android.util.Log
import com.pokemoniquiiSdk.common.Response
import com.pokemoniquiiSdk.common.exceptions.ResponseErrorWith4xxException
import com.pokemoniquiiSdk.common.exceptions.ResponseException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

object PokemonSessionManager {

    @SuppressLint("CheckResult")
    fun <T : Response> executeRequest(requestObservable: Single<T>): Single<T> {
        requestObservable
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                if (it is ResponseException) {
                    //continue
                }
                if (it is ResponseErrorWith4xxException) {
                    if (it.statusCode == 401) {
                        Log.d("info", "401 as response, refresh token and retry the call")
                        return@onErrorResumeNext Single.error { it }
                    }
                }
                if (it is HttpException) {
                    if (it.code() == 401) {
                        Log.d("info", "401 as response, refresh token and retry the call")
                        return@onErrorResumeNext Single.error { it }
                    }
                }
                return@onErrorResumeNext Single.error { it }
            }
        return requestObservable
    }
}


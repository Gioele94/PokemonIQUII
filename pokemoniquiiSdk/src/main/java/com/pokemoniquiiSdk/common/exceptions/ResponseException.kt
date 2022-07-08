package com.pokemoniquiiSdk.common.exceptions

import com.google.gson.annotations.SerializedName

open class ResponseException : Exception {

    internal constructor(errorMessage: String, throwable: Throwable) : super(
        errorMessage,
        throwable
    )

    internal constructor() : super()

    var statusCode: Int = 0

    @SerializedName("error")
    var pokemonErrorCode: Int = -1

    @SerializedName("message")
    var pokemonErrorMessage: String = ""

}


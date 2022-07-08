package com.pokemoniquiiSdk.common

import com.google.gson.annotations.SerializedName

open class Response {

    @SerializedName("error")
    var error : Int = -1
    @SerializedName("message")
    var message : String? = ""

    fun setup(response : Response) {
        error = response.error
        message = response.message

    }
}

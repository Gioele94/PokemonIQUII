package com.pokemoniquiiSdk.common.exceptions

class ResponseErrorWith4xxException : ResponseException {

    constructor(errorMessage : String, throwable : Throwable) : super(errorMessage, throwable)

}
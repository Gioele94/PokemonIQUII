package com.pokemoniquiiSdk.common.exceptions

class ResponseErrorWith5xxException : ResponseException {

    constructor(errorMessage: String, throwable: Throwable) : super(errorMessage, throwable)

}
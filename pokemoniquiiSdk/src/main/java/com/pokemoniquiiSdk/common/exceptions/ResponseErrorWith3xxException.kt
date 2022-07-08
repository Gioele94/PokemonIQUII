package com.pokemoniquiiSdk.common.exceptions


class ResponseErrorWith3xxException : ResponseException {

    constructor(errorMessage : String, throwable : Throwable) : super(errorMessage, throwable)

}
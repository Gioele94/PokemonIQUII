package com.pokemoniquiiSdk.common

open class ResponseItems <I : Any> : Response() {

    lateinit var results : Array<I>

}
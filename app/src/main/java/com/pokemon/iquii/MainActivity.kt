package com.pokemon.iquii

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pokemoniquiiSdk.PokemonIQUIIServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);


        PokemonIQUIIServiceFactory.pokemonCallService
            .getPokemonDetail(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ response ->
               Toast.makeText(this, "success " +  response, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            })

    }
}
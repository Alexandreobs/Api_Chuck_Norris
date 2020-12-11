package com.example.appchucknorris.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.appchucknorris.R
import com.example.appchucknorris.factorys.ViewModelPiadasFactory
import com.example.appchucknorris.view.adaper.ChuckAdaper.KEYY
import com.example.appchucknorris.viewmodel.ChuckViewModelPiadas
import kotlinx.android.synthetic.main.activity_detalhe.*

class DetalheActivity : AppCompatActivity() {

    lateinit var chuckViewModelPiadas: ChuckViewModelPiadas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        initViewModel()
        getPiadaRandom()
        randomPiadas()
        loading()
    }

    private fun loading() {
        chuckViewModelPiadas.getLoading().observe(this, Observer<Boolean> { loading ->
            if (loading) {
                progressBardetalhe.setVisibility(View.VISIBLE)
            } else {
                progressBardetalhe.setVisibility(View.GONE)
            }
        })
    }

    private fun randomPiadas() {
        buttonSortear.setOnClickListener {
            getPiadaRandom()
        }
    }

    private fun initViewModel() {
        chuckViewModelPiadas = ViewModelProviders.of(this, ViewModelPiadasFactory()).get(ChuckViewModelPiadas::class.java)
    }

    private fun getPiadaRandom() {
        var recebidos = intent.extras?.getString(KEYY)
        chuckViewModelPiadas.getAllPiada(recebidos)
        chuckViewModelPiadas.piadaLiveData.observe(this, Observer {
            textPiadas.text = it
        })
    }
}

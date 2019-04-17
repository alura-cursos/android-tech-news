package br.com.alura.technews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.repository.NoticiaRepository

class ListaNoticiasViewModel(
    private val repository: NoticiaRepository
) : ViewModel() {

    fun buscaTodos() : LiveData<List<Noticia>> {
        val liveData = MutableLiveData<List<Noticia>>()
        repository.buscaTodos(quandoSucesso = { noticiasNovas ->
            liveData.value = noticiasNovas
        }, quandoFalha = {})
        return liveData
    }

}
package br.com.alura.technews.repository

class Resource<T>(
    val dado: T?,
    val erro: String? = null
)
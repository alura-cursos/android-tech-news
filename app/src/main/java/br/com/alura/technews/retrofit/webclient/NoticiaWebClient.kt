package br.com.alura.technews.retrofit.webclient

import br.com.alura.technews.model.Noticia
import br.com.alura.technews.retrofit.AppRetrofit
import br.com.alura.technews.retrofit.service.NoticiaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val REQUISICAO_NAO_SUCEDIDA = "Requisição não sucedida"

class NoticiaWebClient(
    private val service: NoticiaService = AppRetrofit().noticiaService
) {

    private fun <T> executaRequisicao(
        call: Call<T>,
        quandoSucesso: (noticiasNovas: T?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    quandoSucesso(response.body())
                } else {
                    quandoFalha(REQUISICAO_NAO_SUCEDIDA)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                quandoFalha(t.message)
            }
        })
    }

    fun buscaTodas(
        quandoSucesso: (noticiasNovas: List<Noticia>?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        executaRequisicao(
            service.buscaTodas(),
            quandoSucesso,
            quandoFalha
        )
    }

    fun salva(
        noticia: Noticia,
        quandoSucesso: (noticiasNovas: Noticia?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        executaRequisicao(service.salva(noticia), quandoSucesso, quandoFalha)
    }

    fun edita(
        id: Long,
        noticia: Noticia,
        quandoSucesso: (noticiasNovas: Noticia?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        executaRequisicao(service.edita(id, noticia), quandoSucesso, quandoFalha)
    }

    fun remove(
        id: Long,
        quandoSucesso: (noticiasNovas: Void?) -> Unit,
        quandoFalha: (erro: String?) -> Unit
    ) {
        executaRequisicao(service.remove(id), quandoSucesso, quandoFalha)
    }

}

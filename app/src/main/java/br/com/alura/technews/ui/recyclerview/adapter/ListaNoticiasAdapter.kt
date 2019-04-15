package br.com.alura.technews.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import kotlinx.android.synthetic.main.item_noticia.view.*

class ListaNoticiasAdapter(
    private val context: Context,
    private val noticias: MutableList<Noticia> = mutableListOf(),
    var quandoItemClicado: (noticia: Noticia) -> Unit = {}
) : RecyclerView.Adapter<ListaNoticiasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.item_noticia,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = noticias.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.vincula(noticia)
    }

    fun atualiza(noticias: List<Noticia>) {
        notifyItemRangeRemoved(0, this.noticias.size)
        this.noticias.clear()
        this.noticias.addAll(noticias)
        notifyItemRangeInserted(0, this.noticias.size)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var noticia: Noticia

        init {
            itemView.setOnClickListener {
                if (::noticia.isInitialized) {
                    quandoItemClicado(noticia)
                }
            }
        }

        fun vincula(noticia: Noticia) {
            this.noticia = noticia
            itemView.item_noticia_titulo.text = noticia.titulo
            itemView.item_noticia_texto.text = noticia.texto
        }

    }

}

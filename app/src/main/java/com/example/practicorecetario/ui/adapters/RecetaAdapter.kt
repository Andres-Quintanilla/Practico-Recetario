package com.example.recetario.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicorecetario.databinding.ItemRecetaBinding
import com.example.practicorecetario.db.models.Receta
import com.example.recetario.ui.activities.DetalleRecetaActivity

class RecetaAdapter(
    private var recetas: List<Receta>,
    private val onClick: (Receta) -> Unit
) : RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecetaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Receta) {
            binding.txtNombreReceta.text = item.nombre
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetalleRecetaActivity::class.java)
                intent.putExtra("nombre", item.nombre)
                intent.putExtra("ingredientes", item.ingredientes)
                intent.putExtra("preparacion", item.preparacion)
                binding.root.context.startActivity(intent) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecetaBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = recetas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recetas[position])
    }

    fun setData(nuevaLista: List<Receta>) {
        recetas = nuevaLista
        notifyDataSetChanged()
    }
}

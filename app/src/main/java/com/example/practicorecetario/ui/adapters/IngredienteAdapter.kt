package com.example.recetario.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicorecetario.databinding.ItemIngredienteBinding
import com.example.practicorecetario.db.models.Ingrediente


class IngredienteAdapter(
    private val lista: List<Ingrediente>,
    private val onClick: (Ingrediente) -> Unit
) : RecyclerView.Adapter<IngredienteAdapter.ViewHolder>() {

    private val seleccionados = mutableSetOf<Ingrediente>()

    inner class ViewHolder(val binding: ItemIngredienteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ingrediente) {
            binding.txtIngrediente.text = item.nombre
            val id = binding.root.context.resources.getIdentifier(
                item.imagen.substringBeforeLast("."), "drawable", binding.root.context.packageName
            )

            binding.imgIngrediente.setImageResource(id)

            binding.root.setOnClickListener {
                if (seleccionados.contains(item)) {
                    seleccionados.remove(item)
                    binding.root.alpha = 1.0f
                } else {
                    seleccionados.add(item)
                    binding.root.alpha = 0.6f
                }
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredienteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lista[position])
    }

    fun getSeleccionados(): List<Ingrediente> = seleccionados.toList()
}

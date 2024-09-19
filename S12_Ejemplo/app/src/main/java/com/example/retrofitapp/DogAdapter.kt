package com.example.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Definición de la clase DogAdapter que extiende RecyclerView.Adapter y toma una lista de imágenes de perros como parámetro
class DogAdapter(private val images: List<String>?) :
    RecyclerView.Adapter<DogViewHolder>() {

    // Método que se llama cuando RecyclerView necesita un nuevo ViewHolder para representar un elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    // Método que se llama para mostrar los datos en una posición específica
    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(images!![position])
    }


    // Método que devuelve el número total de elementos en el conjunto de datos
    override fun getItemCount(): Int {
        // Si la lista de imágenes no es nula, devuelve su tamaño; de lo contrario, devuelve 0
        return images?.size ?: 0
    }
}
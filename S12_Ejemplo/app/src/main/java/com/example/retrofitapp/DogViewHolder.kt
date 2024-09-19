package com.example.retrofitapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

// Definición de la clase DogViewHolder que extiende RecyclerView.ViewHolder y toma una vista como parámetro en su constructor
class DogViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

    // Declaración de una instancia de ItemDogBinding para acceder a las vistas en el diseño del elemento de perro
    private val itemDogBinding: ItemDogBinding

    // Inicialización del ViewHolder
    init {
        // Vinculación de la vista a la clase de enlace generada para el diseño del elemento de perro
        itemDogBinding = ItemDogBinding.bind(view!!)
    }

    // Método para vincular la URL de la imagen de perro al ImageView en el diseño del elemento de perro
    fun bind(imageUrl: String?) {
        // Carga la imagen desde la URL usando Picasso y la muestra en el ImageView ivDog en el diseño del elemento de perro
        Picasso.get().load(imageUrl).into(itemDogBinding.ivDog)
    }
}



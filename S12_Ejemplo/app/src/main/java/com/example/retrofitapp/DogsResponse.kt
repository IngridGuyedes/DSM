package com.example.retrofitapp

import com.google.gson.annotations.SerializedName

// Definición de la clase DogsResponse que representa la respuesta de la API de perros
class DogsResponse {

    // Campo que representa el estado de la respuesta de la API
    @SerializedName("status")
    private var status: String? = null

    // Campo que representa la lista de URLs de imágenes de perros
    @SerializedName("message")
    private var images: List<String?>? = null

    // Método getter para obtener el estado de la respuesta
    fun getStatus(): String? {
        return status
    }

    // Método setter para establecer el estado de la respuesta
    fun setStatus(status: String?) {
        this.status = status
    }

    // Método getter para obtener la lista de URLs de imágenes de perros
    fun getImages(): List<String?>? {
        return images
    }

    // Método setter para establecer la lista de URLs de imágenes de perros
    fun setImages(images: List<String?>?) {
        this.images = images
    }
}

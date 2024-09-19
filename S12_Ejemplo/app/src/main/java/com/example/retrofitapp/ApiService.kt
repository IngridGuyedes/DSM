package com.example.retrofitapp

// Importaciones necesarias de Retrofit para trabajar con llamadas HTTP y anotaciones de métodos
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Definición de la interfaz ApiService, que define los endpoints de la API REST
interface ApiService {

    // Método para obtener imágenes de perros por raza
    // @GET indica que es una solicitud HTTP GET
    // @Path se utiliza para agregar el valor de la variable raza a la URL
    @GET("{raza}/images")
    fun getDogsByBreed(@Path("raza") raza: String?): Call<DogsResponse?>?

    // Método para crear un nuevo perro en la API
    // @POST indica que es una solicitud HTTP POST
    @POST("dogs")
    fun createDog(): Call<DogsResponse?>?

    // Método para actualizar la información de un perro existente en la API
    // @PUT indica que es una solicitud HTTP PUT
    // @Path se utiliza para agregar el valor de la variable id a la URL
    @PUT("dogs/{id}")
    fun updateDog(@Path("id") id: String?): Call<DogsResponse?>?

    // Método para eliminar un perro de la API
    // @DELETE indica que es una solicitud HTTP DELETE
    // @Path se utiliza para agregar el valor de la variable id a la URL
    @DELETE("dogs/{id}")
    fun deleteDog(@Path("id") id: String?): Call<DogsResponse?>?
}


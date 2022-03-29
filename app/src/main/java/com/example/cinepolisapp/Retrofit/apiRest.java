package com.example.cinepolisapp.Retrofit;

import com.example.cinepolisapp.entidades.Peliculas;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiRest {
    @GET("login")
    Call<List<Usuario>> getUsuario();

    @GET("peliculas")
    Call<List<Peliculas>> getPeliculas();

    @POST("registro")
    Call<List<Usuario>> registrarUsuario(@Body Usuario usuario);

    @GET("getPelicula")
    Call<List<Peliculas>> getListaPeliculas();
}
package com.example.cinepolisapp.Retrofit;

import com.example.cinepolisapp.entidades.Alimento;
import com.example.cinepolisapp.entidades.Peliculas;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiRest {

/*SECCION TRANSACCIONES CON BASES DE DATOS DEL SECTOR DE CLIENTES*/
    @GET("login")
    Call<List<Usuario>> getUsuario();
    @POST("registro")
    Call<List<Usuario>> registrarUsuario(@Body Usuario usuario);
    @POST("modCliente")
    Call<List<Usuario>> modCliente(@Body Usuario usuario);
    @POST("eliCliente")
    Call<List<Usuario>> eliCliente(@Body Usuario usuario);


/*SECCION TRANSACCIONES CON BASES DE DATOS DEL SECTOR DE PELICULAS*/
    @GET("peliculas")
    Call<List<Peliculas>> getPeliculas();
    @GET("getPelicula")
    Call<List<Peliculas>> getListaPeliculas();
    @POST("addPelicula")
    Call<List<Peliculas>> addPeliculas(@Body Peliculas pelicula);
    @POST("modPelicula")
    Call<List<Peliculas>> modPelicula(@Body Peliculas pelicula);
    @POST("eliPelicula")
    Call<List<Peliculas>> eliPelicula(@Body Peliculas pelicula);

/*SECCION TRANSACCIONES CON BASES DE DATOS DEL SECTOR DE ALIMENTOS*/
    @GET("getAlimento")
    Call<List<Alimento>> getAlimento();
    @POST("modAlimento")
    Call<List<Alimento>> modAlimento(@Body Alimento alimento);
    @POST("eliAlimento")
    Call<List<Alimento>> eliAlimento(@Body Alimento alimento);
}
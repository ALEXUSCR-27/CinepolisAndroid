package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Peliculas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarPeliculaActivity extends AppCompatActivity {
    private EditText Titulo;
    private EditText Actores;
    private EditText Director;
    private EditText Lenguajes;
    private EditText Generos;
    private EditText Edad;
    private EditText Year;
    private EditText PN;
    private EditText PM;
    private EditText PG;
    private EditText Duracion;

    private apiRest api;

    private Button  btnAgregar;

    private String title;
    private String director;
    private String actors;
    private String languages;
    private String genres;
    private int runtimeMin;
    private int year;
    private int ageRequired;
    private int priceN;
    private int priceG;
    private int priceM;
    private boolean display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pelicula);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        Titulo = findViewById(R.id.Titulo);
        Actores = findViewById(R.id.Actores);
        Director = findViewById(R.id.Director);
        Lenguajes = findViewById(R.id.Lenguajes);
        Generos = findViewById(R.id.Generos);
        Edad = findViewById(R.id.Edad);
        Year = findViewById(R.id.year);
        PN = findViewById(R.id.PN);
        PM = findViewById(R.id.PM);
        PG = findViewById(R.id.PG);
        Duracion = findViewById(R.id.Duracion);

        btnAgregar = findViewById(R.id.Agregar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Titulo.getText().toString().isEmpty() || Actores.getText().toString().isEmpty() || Director.getText().toString().isEmpty() || Lenguajes.getText().toString().isEmpty() ||
                Generos.getText().toString().isEmpty() || Edad.getText().toString().isEmpty() || Year.getText().toString().isEmpty() || PN.getText().toString().isEmpty() ||
                PG.getText().toString().isEmpty() || Duracion.getText().toString().isEmpty()) {
                    AlertDialog.Builder error2 = new AlertDialog.Builder(AgregarPeliculaActivity.this);
                    error2.setMessage("Debe de llenar todos los campos")
                            .setTitle("Error de registro de película")
                            .setCancelable(false)
                            .setIcon(R.drawable.error_icon)
                            .setNegativeButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    error2.show();
                }
                else {
                    title = Titulo.getText().toString();
                    director = Director.getText().toString();
                    actors = Actores.getText().toString();
                    languages = Lenguajes.getText().toString();
                    genres = Generos.getText().toString();
                    runtimeMin = Integer.parseInt(Duracion.getText().toString());
                    year = Integer.parseInt(Year.getText().toString());
                    ageRequired = Integer.parseInt(Edad.getText().toString());
                    priceN = Integer.parseInt(PN.getText().toString());
                    priceG = Integer.parseInt(PG.getText().toString());
                    priceM = Integer.parseInt(PM.getText().toString());
                    Peliculas pelicula = new Peliculas(0,title,director,actors,languages,genres,runtimeMin,year,ageRequired,priceN,priceG,priceM,false);
                    agregarPelicula(pelicula);
                }

            }
        });
    }

    private void agregarPelicula(Peliculas pelicula) {
        Call<List<Peliculas>> call = api.addPeliculas(pelicula);
        call.enqueue(new Callback<List<Peliculas>>() {
            @Override
            public void onResponse(Call<List<Peliculas>> call, Response<List<Peliculas>> response) {
                Log.i("Registro","Pelicula agregada con exito");
            }

            @Override
            public void onFailure(Call<List<Peliculas>> call, Throwable t) {
                Log.i("Registro","Error al agregar pelicula");
            }
        });
        Toast.makeText(this,"Se agregó correctamente la película",Toast.LENGTH_LONG).show();
        finish();
    }
}
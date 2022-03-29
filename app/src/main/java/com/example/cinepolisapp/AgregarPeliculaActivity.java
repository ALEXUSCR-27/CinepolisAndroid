package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    private Button  btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pelicula);

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
    }
}
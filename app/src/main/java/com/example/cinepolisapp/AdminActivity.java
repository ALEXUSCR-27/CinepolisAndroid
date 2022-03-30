package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    private Button peliculas;
    private Button alimentos;
    private Button clientes;
    private Button cartelera;
    private Button cerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        peliculas = findViewById(R.id.btnPeliculas);
        alimentos = findViewById(R.id.btnAlimentos);
        clientes = findViewById(R.id.btnClientes);
        cartelera = findViewById(R.id.btncartelera);
        cerrarSesion = findViewById(R.id.cerrarSesion);

        peliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeliculasAdmin();
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ClientesAdmin();}
        });

        alimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlimentosAdmin();
            }
        });


    }

    private void PeliculasAdmin() {
        Intent pelis = new Intent(this,PeliculasAdminActivity.class);
        startActivity(pelis);
    }

    private void ClientesAdmin() {
        Intent clientes = new Intent(this,ClientesAdminActivity.class);
        startActivity(clientes);
    }

    private void AlimentosAdmin() {
        Intent alimentos = new Intent(this,AlimentosAdminActivity.class);
        startActivity(alimentos);
    }
}
package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;

public class ClientesAdminActivity extends AppCompatActivity {
    private EditText idPelicula;
    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_admin);

        idPelicula = findViewById(R.id.idPelicula);
        btnBuscar = findViewById(R.id.Buscar);
    }

}
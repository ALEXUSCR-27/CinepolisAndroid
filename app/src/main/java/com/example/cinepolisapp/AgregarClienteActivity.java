package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AgregarClienteActivity extends AppCompatActivity {
    private EditText NumeroCedula, Nombre, PrimerApellido, SegundoApellido, CorreoUsuario;
    private EditText Edad, FechaNacimiento, Vacunacion;
    private Button Guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        NumeroCedula = findViewById(R.id.NumeroCedula);
        Nombre = findViewById(R.id.Nombre);
        PrimerApellido = findViewById(R.id.PrimerApellido);
        SegundoApellido = findViewById(R.id.SegundoApellido);
        CorreoUsuario = findViewById(R.id.CorreoUsuario);
        Edad = findViewById(R.id.Edad);
        FechaNacimiento = findViewById(R.id.FechaNacimiento);
        Vacunacion = findViewById(R.id.Vacunacion);
        Guardar = findViewById(R.id.Guardar);

    }
}
package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cinepolisapp.entidades.Peliculas;

public class ModificarClienteActivity extends AppCompatActivity {
    private EditText NumeroCedula, Nombre, PrimerApellido, SegundoApellido, CorreoUsuario;
    private EditText Edad, FechaNacimiento, Vacunacion,password;
    private Button Guardar,Eliminar;
    private TextView NombreCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);


        NombreCliente = findViewById(R.id.NombreCliente);

        NumeroCedula = findViewById(R.id.NumeroCedula);
        Nombre = findViewById(R.id.Nombre);
        PrimerApellido = findViewById(R.id.PrimerApellido);
        SegundoApellido = findViewById(R.id.SegundoApellido);
        CorreoUsuario = findViewById(R.id.CorreoUsuario);
        Edad = findViewById(R.id.Edad);
        FechaNacimiento = findViewById(R.id.FechaNacimiento);
        Vacunacion = findViewById(R.id.Vacunacion);
        password = findViewById(R.id.password);

        Guardar = findViewById(R.id.Guardar);
        Eliminar = findViewById(R.id.Eliminar);


    }
}
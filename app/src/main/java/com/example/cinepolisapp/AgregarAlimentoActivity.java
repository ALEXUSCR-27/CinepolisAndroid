package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Alimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarAlimentoActivity extends AppCompatActivity {
    private EditText Nombre, Precio, CantidadDisponible, Tipo;
    private Button btnAgregar;

    private int precio, cantidad, tipo;

    private Alimento alimento;
    private apiRest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alimento);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        Nombre = findViewById(R.id.Nombre);
        Precio = findViewById(R.id.precio);
        CantidadDisponible = findViewById(R.id.cantidadDisponible);
        Tipo = findViewById(R.id.Tipo);

        btnAgregar = findViewById(R.id.agregarAlimento);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarAlimento();
            }
        });
    }

    private void AgregarAlimento() {
        if (Nombre.getText().toString().isEmpty() || Precio.getText().toString().isEmpty() || CantidadDisponible.getText().toString().isEmpty() ||
                Tipo.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(AgregarAlimentoActivity.this);
            error2.setMessage("Debe de llenar todos los campos")
                    .setTitle("Error de registro de alimento")
                    .setCancelable(false)
                    .setIcon(R.drawable.error_icon)
                    .setNegativeButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            error2.show();
        } else {
            precio = Integer.parseInt(Precio.getText().toString());
            cantidad = Integer.parseInt(CantidadDisponible.getText().toString());
            tipo = Integer.parseInt(Tipo.getText().toString());

            alimento = new Alimento(0, Nombre.getText().toString(), cantidad, tipo, precio);
            Call<List<Alimento>> call = api.addAlimento(alimento);
            call.enqueue(new Callback<List<Alimento>>() {
                @Override
                public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {

                }

                @Override
                public void onFailure(Call<List<Alimento>> call, Throwable t) {

                }
            });
            Toast.makeText(this, "Registro realizado con exito", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Alimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarAlimentoActivity extends AppCompatActivity {

    private EditText Nombre, Precio, CantidadDisponible, Tipo;
    private Button btnEliminar, btnGuardar;
    private TextView NombreAlimento;
    private apiRest api;

    private Alimento alimento;

    private int precio,cantidad,tipo;
    private String precio2,cantidad2,tipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_alimento);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        alimento = (Alimento) getIntent().getSerializableExtra("Alimento");

        Nombre = findViewById(R.id.Nombre);
        Precio = findViewById(R.id.precio);
        CantidadDisponible = findViewById(R.id.cantidadDisponible);
        Tipo = findViewById(R.id.Tipo);
        NombreAlimento = findViewById(R.id.NombreAlimento);

        btnEliminar = findViewById(R.id.Eliminar);
        btnGuardar = findViewById(R.id.Guardar);

        precio2 = alimento.getPrice()+"";
        cantidad2 = alimento.getStock()+"";
        tipo2 = alimento.getType()+"";
        /*quitar tipo*/

        NombreAlimento.setText(alimento.getName());
        Nombre.setText(alimento.getName());
        CantidadDisponible.setText(cantidad2);
        Tipo.setText(tipo2);
        Precio.setText(precio2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardaAlimento();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarAlimento();
            }
        });
    }

    private void GuardaAlimento() {
        if (Nombre.getText().toString().isEmpty() || Precio.getText().toString().isEmpty() || CantidadDisponible.getText().toString().isEmpty() ||
            Tipo.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(ModificarAlimentoActivity.this);
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
        }
        else {
            precio = Integer.parseInt(Precio.getText().toString());
            cantidad = Integer.parseInt(CantidadDisponible.getText().toString());
            tipo = Integer.parseInt(Tipo.getText().toString());
            Alimento alimentoMod = new Alimento(alimento.getFoodID(),Nombre.getText().toString(),cantidad,tipo,precio);

            Call<List<Alimento>> call = api.modAlimento(alimentoMod);
            call.enqueue(new Callback<List<Alimento>>() {
                @Override
                public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                    Log.i("Registro","Registro realizado con exito");
                }

                @Override
                public void onFailure(Call<List<Alimento>> call, Throwable t) {
                    Log.i("Registro","Error al realizar la modificacion");
                }
            });
            Toast.makeText(this,"Cambios realizados con exito",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void EliminarAlimento() {
        Call<List<Alimento>> call = api.eliAlimento(alimento);
        call.enqueue(new Callback<List<Alimento>>() {
            @Override
            public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                Log.i("Registro","Se elimino el cliente con exito");
            }

            @Override
            public void onFailure(Call<List<Alimento>> call, Throwable t) {
                Log.i("Registro","Error al eliminar el alimento");
            }
        });
        Toast.makeText(this,"Se eliminó el alimento con exito",Toast.LENGTH_LONG).show();
        finish();
    }
}
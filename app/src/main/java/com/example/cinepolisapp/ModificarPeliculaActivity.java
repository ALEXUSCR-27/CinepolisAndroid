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
import com.example.cinepolisapp.entidades.Peliculas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarPeliculaActivity extends AppCompatActivity {

    private TextView NombrePelicula;
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

    private Button btnGuardar;
    private Button btnEliminar;

    private int runtimeMin;
    private int year;
    private int ageRequired;
    private int priceN;
    private int priceG;
    private int priceM;

    private String Edad2;
    private String Year2;
    private String PN2;
    private String PM2;
    private String PG2;
    private String Duracion2;
    private Peliculas pelicula;

    private apiRest api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_pelicula);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        pelicula = (Peliculas) getIntent().getSerializableExtra("Pelicula");

        NombrePelicula = findViewById(R.id.NombrePelicula);
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

        btnEliminar = findViewById(R.id.Eliminar);
        btnGuardar = findViewById(R.id.Guardar);

        NombrePelicula.setText(pelicula.getTitle());
        Titulo.setText(pelicula.getTitle());

        Edad2 = pelicula.getAgeRequired()+"";
        Year2 = pelicula.getYear()+"";
        PN2 = pelicula.getPriceN()+"";
        PM2 = pelicula.getPriceM()+"";
        PG2 = pelicula.getPriceG()+"";
        Duracion2 = pelicula.getRuntimeMin()+"";

        Actores.setText(pelicula.getActors());
        Director.setText(pelicula.getDirector());
        Lenguajes.setText(pelicula.getLanguages());
        Generos.setText(pelicula.getGenres());
        Edad.setText(Edad2);
        Year.setText(Year2);
        PN.setText(PN2);
        PM.setText(PM2);
        PG.setText(PG2);
        Duracion.setText(Duracion2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCambios();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPelicula();
            }
        });
    }

    private void guardarCambios() {
        if (Titulo.getText().toString().isEmpty() || Actores.getText().toString().isEmpty() || Director.getText().toString().isEmpty() || Lenguajes.getText().toString().isEmpty() ||
                Generos.getText().toString().isEmpty() || Edad.getText().toString().isEmpty() || Year.getText().toString().isEmpty() || PN.getText().toString().isEmpty() ||
                PG.getText().toString().isEmpty() || Duracion.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(ModificarPeliculaActivity.this);
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
            runtimeMin = Integer.parseInt(Duracion.getText().toString());
            year = Integer.parseInt(Year.getText().toString());
            ageRequired = Integer.parseInt(Edad.getText().toString());
            priceN = Integer.parseInt(PN.getText().toString());
            priceG = Integer.parseInt(PG.getText().toString());
            priceM = Integer.parseInt(PM.getText().toString());
            Peliculas peliculaMod = new Peliculas(pelicula.getMovieID(),Titulo.getText().toString(),Director.getText().toString(),Actores.getText().toString(),Lenguajes.getText().toString(),
                    Generos.getText().toString(),runtimeMin,year,ageRequired,priceN,priceG,priceM,pelicula.isDisplay());

            Call<List<Peliculas>> call = api.modPelicula(peliculaMod);
            call.enqueue(new Callback<List<Peliculas>>() {
                @Override
                public void onResponse(Call<List<Peliculas>> call, Response<List<Peliculas>> response) {
                    Log.i("Registro","Registro realizado con exito");
                }

                @Override
                public void onFailure(Call<List<Peliculas>> call, Throwable t) {
                    Log.i("Registro","Error al realizar la modificacion");
                }
            });
            Toast.makeText(this,"Cambios realizados con exito",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void eliminarPelicula() {
        Call<List<Peliculas>> call = api.eliPelicula(pelicula);
        call.enqueue(new Callback<List<Peliculas>>() {
            @Override
            public void onResponse(Call<List<Peliculas>> call, Response<List<Peliculas>> response) {
                Log.i("Registro","Se elimino la pelicula con exito");
            }

            @Override
            public void onFailure(Call<List<Peliculas>> call, Throwable t) {
                Log.i("Registro","Error al eliminar la pelicula");
            }
        });
        Toast.makeText(this,"Se eliminó la película con exito",Toast.LENGTH_LONG).show();
        finish();
    }
}
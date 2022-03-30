package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Peliculas;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculasAdminActivity extends AppCompatActivity {
    private EditText idPelicula;
    private Button btnBuscar;
    private ListView lista;
    private Button agregarPelicula;
    private List<String> resultados = new ArrayList<>();
    private Peliculas pelicula;
    private ArrayAdapter<String> adapter;
    private apiRest api;

    private int idMovie;
    private int idPeli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_admin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        btnBuscar = findViewById(R.id.Buscar);
        idPelicula = findViewById(R.id.idPelicula);
        lista = findViewById(R.id.resultadosPeliculas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("seleccion",resultados.get(i).toString());
                modificaPelicula();
            }
        });
        agregarPelicula = findViewById(R.id.agregarPelicula);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarPelicula();
            }
        });

        agregarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { agregarP(); }
        });
    }

    private void modificaPelicula() {
        Intent modificarPelicula = new Intent(this,ModificarPeliculaActivity.class);
        modificarPelicula.putExtra("Pelicula",pelicula);
        startActivity(modificarPelicula);
    }

    private void agregarP() {
        Intent agregaPelis = new Intent(this,AgregarPeliculaActivity.class);
        startActivity(agregaPelis);
    }
    private void BuscarPelicula() {
        if (idPelicula.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(PeliculasAdminActivity.this);
            error2.setMessage("Debe de llenar todos los campos")
                    .setTitle("Error de busqueda de películas")
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
            idPeli = Integer.parseInt(idPelicula.getText().toString());
            idPelicula.getText().clear();
            Call<List<Peliculas>> call = api.getListaPeliculas();
            call.enqueue(new Callback<List<Peliculas>>() {
                @Override
                public void onResponse(Call<List<Peliculas>> call, Response<List<Peliculas>> response) {
                    List<Peliculas> peliculas = response.body();
                    presentarResultados(peliculas);
                }

                @Override
                public void onFailure(Call<List<Peliculas>> call, Throwable t) {
                    Log.i("si","error");
                }
            });
        }
    }
    private void presentarResultados(List<Peliculas> peliculas) {
        for (Peliculas pe : peliculas) {
            if (idPeli == pe.getMovieID()) {
                resultados.add(pe.getTitle());
                pelicula = pe;
                break;
            }
        }
        if (resultados.isEmpty()) {
            resultados.add("Sin resultados");
        }
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);

    }
}
package com.example.cinepolisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Alimento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlimentosAdminActivity extends AppCompatActivity {

    private EditText idAlimento;
    private Button Buscar;
    private Button agregarAlimento;
    private ListView lista;

    private List<String> resultados;
    ArrayAdapter<String> adapter;
    private apiRest api;

    private int idAlimento2;

    private Alimento alimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_admin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        idAlimento = findViewById(R.id.idAlimento);
        Buscar = findViewById(R.id.Buscar);
        agregarAlimento = findViewById(R.id.agregarAlimento);
        lista = findViewById(R.id.resultadosAlimentos);

        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarAlimento();
            }
        });
        agregarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlimentos();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("seleccion","si");
                if (!resultados.get(i).toString().equals("Sin resultados")){
                    modificarAlimento();
                }
            }
        });
    }

    private void agregarAlimentos() {
        idAlimento.getText().clear();
        Intent agregaAlimentos = new Intent(this,AgregarAlimentoActivity.class);
        startActivity(agregaAlimentos);

    }
    private void BuscarAlimento() {
        resultados = new ArrayList<>();
        if (idAlimento.getText().toString().isEmpty()) {idAlimento.setError("Por favor ingresa el número de identificación del alimento");}
        else {
            idAlimento2 = Integer.parseInt(idAlimento.getText().toString());
            idAlimento.getText().clear();

            Call<List<Alimento>> call = api.getAlimento();
            call.enqueue(new Callback<List<Alimento>>() {
                @Override
                public void onResponse(Call<List<Alimento>> call, Response<List<Alimento>> response) {
                    List<Alimento> alimentos = response.body();
                    presentarResultados(alimentos);
                }

                @Override
                public void onFailure(Call<List<Alimento>> call, Throwable t) {

                }
            });
        }
    }

    private void modificarAlimento() {
        Intent modificarAlimentos = new Intent(this,ModificarAlimentoActivity.class);
        modificarAlimentos.putExtra("Alimento",alimento);

        resultados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);

        startActivity(modificarAlimentos);
    }

    private void presentarResultados(List<Alimento> alimentos) {
        for(Alimento al:alimentos) {
            if (idAlimento2 == al.getFoodID()) {
                resultados.add(al.getName());
                alimento = al;
                break;
            }
        }
        if (resultados.isEmpty()) {resultados.add("Sin resultados");}
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);
    }
}
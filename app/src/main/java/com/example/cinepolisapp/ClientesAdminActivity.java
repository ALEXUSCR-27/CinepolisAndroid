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
import com.example.cinepolisapp.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientesAdminActivity extends AppCompatActivity {
    private EditText cedulaCliente;
    private Button btnBuscar;
    private Button agregarCliente;

    private ListView lista;
    private List<String> resultados;
    ArrayAdapter<String> adapter;
    private apiRest api;
    private int cedula;

    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_admin);

        cedulaCliente = findViewById(R.id.cedulaCliente);
        btnBuscar = findViewById(R.id.Buscar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        btnBuscar = findViewById(R.id.Buscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarClientes();
            }
        });

        agregarCliente = findViewById(R.id.agregarCliente);

        agregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarC();
            }
        });

        lista = findViewById(R.id.resultadosClientes);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("seleccion","si");
                if (!resultados.get(i).toString().equals("Sin resultados")){
                    modificarCliente();
                }
            }
        });
    }

    private void agregarC() {
        cedulaCliente.getText().clear();
        Intent agregaClientes = new Intent(this,AgregarClienteActivity.class);
        startActivity(agregaClientes);
    }

    private void buscarClientes() {
        resultados = new ArrayList<>();
        if (cedulaCliente.getText().toString().isEmpty()) {
            cedulaCliente.setError("Por favor ingresa el número de identificación del cliente");
        }
        else {
            cedula = Integer.parseInt(cedulaCliente.getText().toString());
            cedulaCliente.getText().clear();

            Call<List<Usuario>> call = api.getUsuario();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    List<Usuario> usuarios = response.body();
                    presentarResultados(usuarios);
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {

                }
            });
        }
    }

    private void modificarCliente() {
        Intent modificarCliente = new Intent(this,ModificarClienteActivity.class);
        modificarCliente.putExtra("Cliente",usuario);

        resultados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);

        startActivity(modificarCliente);
    }

    private void presentarResultados(List<Usuario> usuarios) {
        for (Usuario us :usuarios) {
            if (cedula == us.getID()) {
                resultados.add(us.getName());
                usuario = us;
                break;
            }
        }
        if (resultados.isEmpty()) { resultados.add("Sin resultados");}
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);
    }

}
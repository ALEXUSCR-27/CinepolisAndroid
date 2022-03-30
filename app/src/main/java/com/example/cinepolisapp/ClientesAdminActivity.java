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
    private List<String> resultados = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private apiRest api;
    private int cedula;


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
        cedulaCliente = findViewById(R.id.cedulaCliente);

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
            }
        });
    }

    private void agregarC() {
        Intent agregaClientes = new Intent(this,AgregarClienteActivity.class);
        startActivity(agregaClientes);
    }

    private void buscarClientes() {
        if (cedulaCliente.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(ClientesAdminActivity.this);
            error2.setMessage("Debe de llenar todos los campos")
                    .setTitle("Error de busqueda de clientes")
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
            cedula = Integer.parseInt(cedulaCliente.getText().toString());
            cedulaCliente.getText().clear();

            Call<List<Usuario>> call = api.getClientes();
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

    private void presentarResultados(List<Usuario> usuarios) {
        for (Usuario us :usuarios) {
            if (cedula == us.getID()) {
                resultados.add(us.getName());
            }
        }
        if (resultados.isEmpty()) {
            resultados.add("Sin resultados");
        }
        adapter = new ArrayAdapter<>(this,R.layout.style_item,resultados);
        lista.setAdapter(adapter);
    }

}
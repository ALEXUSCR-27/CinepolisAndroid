package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Peliculas;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private apiRest api;
    private Button btnIngresar;
    private Button btnRegistro;
    private EditText correo;
    private EditText contraseña;
    private int admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.buttonlogin);
        btnRegistro = findViewById(R.id.buttonRegistrarse);
        correo = findViewById(R.id.NombreUsuario);
        contraseña = findViewById(R.id.editTextTextPassword);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correo.getText().toString().isEmpty()){
                    correo.setError("Por favor ingresa tu correo");
                }
                else if (contraseña.getText().toString().isEmpty()){
                    contraseña.setError("Por favor ingresa tu contraseña");
                }
                else{ getUsuario();}
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });
    }
    private void getUsuario() {
        Call<List<Usuario>> call = api.getUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isEmpty()) {
                        AlertDialog.Builder error = new AlertDialog.Builder(LoginActivity.this);
                        error.setMessage("Correo y contraseña incorrectos")
                                .setCancelable(false)
                                .setIcon(R.drawable.error_icon)
                                .setNegativeButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog titulo = error.create();
                        titulo.setTitle("Error de inicio de sesión");
                        titulo.show();

                    }
                    else {
                        List<Usuario> usuarios = response.body();
                        realizarLogin(usuarios);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("Revision", "Error de transaccion");
            }
        });
    }

    private void realizarLogin (List<Usuario> usuarios) {
        AlertDialog.Builder error2 = new AlertDialog.Builder(LoginActivity.this);

        for (Usuario us : usuarios) {
            Log.i("error",us.getPassword());
            if (correo.getText().toString().equals(us.getEmail()) && contraseña.getText().toString().equals(us.getPassword())) {
                correo.getText().clear();
                contraseña.getText().clear();
                if (us.isType()) {
                    Intent admin = new Intent(this, AdminActivity.class);
                    startActivity(admin);
                    return;
                }
                else {
                    Intent main = new Intent(this, MainActivity.class);
                    startActivity(main);
                    return;
                }
            }
        }
        error2.setMessage("Correo y contraseña incorrectos")
                .setTitle("Error de inicio de sesión")
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

    private void registro() {
        correo.getText().clear();
        contraseña.getText().clear();
        Intent registro = new Intent (this,RegistroActivity.class);
        startActivity(registro);
    }
}
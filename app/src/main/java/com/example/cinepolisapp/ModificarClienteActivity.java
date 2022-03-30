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
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarClienteActivity extends AppCompatActivity {
    private EditText NumeroCedula, Nombre, PrimerApellido, SegundoApellido, CorreoUsuario;
    private EditText Edad, FechaNacimiento, Vacunacion,password;
    private Button Guardar,Eliminar;
    private TextView NombreCliente;
    private Usuario usuario;
    private apiRest api;

    private String cedula;
    private String edad;
    private String vacunacion;

    private int cedula2;
    private int edad2;
    private int vacunacion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cliente);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        usuario = (Usuario) getIntent().getSerializableExtra("Cliente");


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

        NombreCliente.setText(usuario.getName());
        Nombre.setText(usuario.getName());

        cedula = usuario.getID()+"";
        edad = usuario.getAge()+"";
        vacunacion = usuario.getVaccines()+"";

        PrimerApellido.setText(usuario.getSurname1());
        SegundoApellido.setText(usuario.getSurname2());
        CorreoUsuario.setText(usuario.getEmail());
        Edad.setText(edad);
        FechaNacimiento.setText(usuario.getBirthdate());
        Vacunacion.setText(vacunacion);
        password.setText(usuario.getPassword());
        NumeroCedula.setText(cedula);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { guardarCambios();}
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { eliminarCliente();}
        });
    }

    private void guardarCambios() {
        if (Nombre.getText().toString().isEmpty() || PrimerApellido.getText().toString().isEmpty() || SegundoApellido.getText().toString().isEmpty() || CorreoUsuario.getText().toString().isEmpty() ||
            Edad.getText().toString().isEmpty() || FechaNacimiento.getText().toString().isEmpty() || Vacunacion.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
            NumeroCedula.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(ModificarClienteActivity.this);
            error2.setMessage("Debe de llenar todos los campos")
                    .setTitle("Error de registro de cliente")
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
            cedula2 = Integer.parseInt(NumeroCedula.getText().toString());
            edad2 = Integer.parseInt(Edad.getText().toString());
            vacunacion2 = Integer.parseInt(Vacunacion.getText().toString());
            Usuario usuarioMod = new Usuario(usuario.getUserID(),CorreoUsuario.getText().toString(),password.getText().toString(),Nombre.getText().toString(),PrimerApellido.getText().toString(),
                    SegundoApellido.getText().toString(),cedula2,vacunacion2,FechaNacimiento.getText().toString(),edad2,usuario.isType());

            Call<List<Usuario>> call = api.modCliente(usuarioMod);
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    Log.i("Registro","Registro realizado con exito");
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Log.i("Registro","Error al realizar la modificacion");
                }
            });
            Toast.makeText(this,"Cambios realizados con exito",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void eliminarCliente() {
        Call<List<Usuario>> call = api.eliCliente(usuario);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                Log.i("Registro","Se elimino el cliente con exito");
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("Registro","Error al eliminar el cliente");
            }
        });
        Toast.makeText(this,"Se eliminó el usuario con exito",Toast.LENGTH_LONG).show();
        finish();
    }
}
package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarClienteActivity extends AppCompatActivity {
    private EditText NumeroCedula, Nombre, PrimerApellido, SegundoApellido, CorreoUsuario;
    private EditText Edad, FechaNacimiento;
    private Button Guardar;

    private int cedula;
    private int edad;
    private int vacunacion;

    private Spinner spinner;

    private Usuario usuario;
    private apiRest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        NumeroCedula = findViewById(R.id.NumeroCedula);
        Nombre = findViewById(R.id.Nombre);
        PrimerApellido = findViewById(R.id.PrimerApellido);
        SegundoApellido = findViewById(R.id.SegundoApellido);
        CorreoUsuario = findViewById(R.id.CorreoUsuario);
        Edad = findViewById(R.id.Edad);
        FechaNacimiento = findViewById(R.id.FechaNacimiento);

        spinner = (Spinner) findViewById(R.id.Vacunacion);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.estado_vacunacion,R.layout.style_item);
        adapter.setDropDownViewResource(R.layout.style_droplist_item);
        spinner.setAdapter(adapter);

        Guardar = findViewById(R.id.Guardar);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarCliente();
            }
        });
    }

    private void RegistrarCliente() {
        if (Nombre.getText().toString().isEmpty() || PrimerApellido.getText().toString().isEmpty() || SegundoApellido.getText().toString().isEmpty() || CorreoUsuario.getText().toString().isEmpty() ||
            Edad.getText().toString().isEmpty() || FechaNacimiento.getText().toString().isEmpty() || spinner.getSelectedItem().toString().isEmpty() || NumeroCedula.getText().toString().isEmpty()) {
            AlertDialog.Builder error2 = new AlertDialog.Builder(AgregarClienteActivity.this);
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
            cedula = Integer.parseInt(NumeroCedula.getText().toString());
            edad = Integer.parseInt(Edad.getText().toString());
            vacunacion = Integer.parseInt(spinner.getSelectedItem().toString());
            usuario = new Usuario(0,CorreoUsuario.getText().toString(),"",Nombre.getText().toString(),PrimerApellido.getText().toString(),
                    SegundoApellido.getText().toString(),cedula,vacunacion,FechaNacimiento.getText().toString(),edad,false);
            Call<List<Usuario>> call = api.getUsuario();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    List<Usuario> usuarios = response.body();
                    RevisarRegistro(usuario,usuarios);
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {

                }
            });
        }
    }

    private void RevisarRegistro(Usuario usuario,List<Usuario> usuarios) {
        if(RevisarRegistro_aux(usuario,usuarios)) {
            Call<List<Usuario>> call = api.registrarUsuario(usuario);
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {

                }
            });
            Toast.makeText(this,"Registro realizado con exito",Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            AlertDialog.Builder error2 = new AlertDialog.Builder(AgregarClienteActivity.this);
            error2.setMessage("Ya existe un usuario con la misma cédula")
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


    }


    private boolean RevisarRegistro_aux(Usuario usuario,List<Usuario> usuarios) {
        for (Usuario us:usuarios) { if ( us.getID() == usuario.getID()) { return false;}}
        return  true;
    }
}
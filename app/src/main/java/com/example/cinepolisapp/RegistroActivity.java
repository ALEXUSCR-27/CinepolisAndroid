package com.example.cinepolisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cinepolisapp.Retrofit.apiRest;
import com.example.cinepolisapp.entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity {
    private Spinner spinner;
    //private String [] listaVacunacion = {"Ninguna","Incompleto","Completo"};
    private EditText Nombre;
    private EditText PApellido;
    private EditText SApellido;
    private EditText Correo;
    private EditText Contrasena;
    private EditText Cedula;
    private EditText FechaNacimiento;
    private Button Registrase;
    private apiRest api;
    private Boolean bandera = true;

    private String CorreoUsuario;
    private String ContrasenaUsuario;
    private String NombreUsuario;
    private String PrimerApellido;
    private String SegundoApellido;
    private String EstadoVacunacion;
    private String CedulaUsuario;
    private String FechaNacimientoU;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:9000/cinepolis-web/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(apiRest.class);

        Nombre = findViewById(R.id.NombreUsuario);
        PApellido = findViewById(R.id.PApellido);
        SApellido = findViewById(R.id.SApellido);
        Correo = findViewById(R.id.CorreoUsuario);
        Cedula = findViewById(R.id.Cedula);
        FechaNacimiento = findViewById(R.id.FechaNacimiento);
        Registrase = findViewById(R.id.buttonRegistro);
        Contrasena = findViewById(R.id.Contrasena);

        spinner = (Spinner) findViewById(R.id.Vacunacion);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.estado_vacunacion,R.layout.style_item);
        spinner.setAdapter(adapter);

        Registrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Nombre.getText().toString().isEmpty() || PApellido.getText().toString().isEmpty() || SApellido.getText().toString().isEmpty() ||
                   Correo.getText().toString().isEmpty() || Cedula.getText().toString().isEmpty() || FechaNacimiento.getText().toString().isEmpty()
                        || spinner.getSelectedItem().toString().isEmpty()) {
                    AlertDialog.Builder error2 = new AlertDialog.Builder(RegistroActivity.this);
                    error2.setMessage("Debe de llenar todos los campos")
                            .setTitle("Error de registro de cuenta")
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
                    CorreoUsuario = Correo.getText().toString();
                    NombreUsuario = Nombre.getText().toString();
                    ContrasenaUsuario = Contrasena.getText().toString();
                    PrimerApellido = PApellido.getText().toString();
                    SegundoApellido = SApellido.getText().toString();
                    EstadoVacunacion = spinner.getSelectedItem().toString();
                    CedulaUsuario = Cedula.getText().toString();
                    FechaNacimientoU = FechaNacimiento.getText().toString();
                    VerificarRegistro();
                }
            }
        });


    }

    private void VerificarRegistro() {
        Usuario usuario = new Usuario(CorreoUsuario,ContrasenaUsuario,NombreUsuario,PrimerApellido,SegundoApellido,EstadoVacunacion,CedulaUsuario,FechaNacimientoU);
        if (VerificarUsuario(usuario)) {
            Log.i("registro","si llego papu:(");
            Call<List<Usuario>> call = api.registrarUsuario(usuario);
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    Log.i("registro","listo papu");
                }

                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Log.i("registro","cagaste");
                }
            });
        }
        bandera = true;
    }

    private boolean VerificarUsuario(Usuario usuario) {
        Call<List<Usuario>> call = api.getUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    List<Usuario> usuarios = response.body();
                    for (Usuario us : usuarios) {
                        if (us.getCorreo().equals(Correo.getText().toString())) {
                            bandera = false;
                            AlertDialog.Builder error2 = new AlertDialog.Builder(RegistroActivity.this);
                            error2.setMessage("Ya existe un usuario vinculado al correo ingresado")
                                    .setTitle("Error de registro de cuenta")
                                    .setCancelable(false)
                                    .setIcon(R.drawable.error_icon)
                                    .setNegativeButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) { dialogInterface.cancel();}
                                    });
                            error2.show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.i("Codigo: ", "no");
            }
        });
        if (bandera) {return true;}
        else {
            bandera = true;
            return false;
        }
    }
}
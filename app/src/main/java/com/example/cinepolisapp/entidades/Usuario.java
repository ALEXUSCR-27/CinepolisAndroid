package com.example.cinepolisapp.entidades;

public class Usuario {
    private String  email;
    private String password;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String EstadoVacunacion;
    private String Cedula;
    private String FechaNacimiento;
    private Boolean type;


    public Usuario(String email, String Password, String nombre, String primerApellido, String segundoApellido, String estadoVacunacion, String cedula, String fechaNacimiento, boolean type) {
        this.email = email;
        password = Password;
        Nombre = nombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        EstadoVacunacion = estadoVacunacion;
        Cedula = cedula;
        FechaNacimiento = fechaNacimiento;
        this.password = password;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean admin) {
        this.type = admin;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        PrimerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        SegundoApellido = segundoApellido;
    }

    public String getEstadoVacunacion() {
        return EstadoVacunacion;
    }

    public void setEstadoVacunacion(String estadoVacunacion) {
        EstadoVacunacion = estadoVacunacion;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }
}

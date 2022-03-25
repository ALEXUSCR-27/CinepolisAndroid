package com.example.cinepolisapp.entidades;

public class Usuario {
    private String  Correo;
    private String Contrasena;
    private String Nombre;
    private String PrimerApellido;
    private String SegundoApellido;
    private String EstadoVacunacion;
    private String Cedula;
    private String FechaNacimiento;


    public Usuario(String correo, String contrasena, String nombre, String primerApellido, String segundoApellido, String estadoVacunacion, String cedula, String fechaNacimiento) {
        Correo = correo;
        Contrasena = contrasena;
        Nombre = nombre;
        PrimerApellido = primerApellido;
        SegundoApellido = segundoApellido;
        EstadoVacunacion = estadoVacunacion;
        Cedula = cedula;
        FechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
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

package com.example.cinepolisapp.entidades;

public class Usuario {
    private int userID;
    private String email;
    private String password;
    private String name;
    private String surname1;
    private String surname2;
    private int ID;
    private int vaccines;
    private String birthdate;
    private int age;
    private boolean type;


    public Usuario(int userID, String email, String password, String name, String surname1, String surname2, int ID, int vaccines, String birthdate, int age, boolean type) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.ID = ID;
        this.vaccines = vaccines;
        this.birthdate = birthdate;
        this.age = age;
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVaccines() {
        return vaccines;
    }

    public void setVaccines(int vaccines) {
        this.vaccines = vaccines;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
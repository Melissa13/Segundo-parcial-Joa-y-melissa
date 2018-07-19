package Clases;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private String username;
    private String nombre;
    private Date date_birth;
    private String place_birth;
    private ArrayList<String> Studies;
    private String workplace;
    private boolean Administrador;

    public User(String username, String nombre, Date date_birth, String place_birth, ArrayList<String> studies, String workplace, boolean administrador) {
        this.username = username;
        this.nombre = nombre;
        this.date_birth = date_birth;
        this.place_birth = place_birth;
        Studies = studies;
        this.workplace = workplace;
        Administrador = administrador;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getPlace_birth() {
        return place_birth;
    }

    public void setPlace_birth(String place_birth) {
        this.place_birth = place_birth;
    }

    public ArrayList<String> getStudies() {
        return Studies;
    }

    public void setStudies(ArrayList<String> studies) {
        Studies = studies;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public boolean isAdministrador() {
        return Administrador;
    }

    public void setAdministrador(boolean administrador) {
        Administrador = administrador;
    }
}

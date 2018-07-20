package Clases;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "User.findAllByUsername", query = "select p from User  p where p.username like :username")})
public class User implements Serializable{
    @Id
    private String username;
    private String Password;
    private String nombre;
    private Date date_birth;
    private String place_birth;
    private String Studies;  //list
    private String workplace;
    private boolean Administrador;
    private String actual_place;
    private String job;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> Friends;
    @OneToMany(mappedBy = "Author", fetch = FetchType.EAGER)
    private Set<image> Albun;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Notification> news;

    public User(String username, String password, String nombre, Date date_birth, String place_birth, String studies, String workplace, boolean administrador, String actual_place, String job, Set<User> friends, Set<image> albun, Set<Notification> news) {
        this.username = username;
        Password = password;
        this.nombre = nombre;
        this.date_birth = date_birth;
        this.place_birth = place_birth;
        Studies = studies;
        this.workplace = workplace;
        Administrador = administrador;
        this.actual_place = actual_place;
        this.job = job;
        Friends = friends;
        Albun = albun;
        this.news = news;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public String getStudies() {
        return Studies;
    }

    public void setStudies(String studies) {
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

    public String getActual_place() {
        return actual_place;
    }

    public void setActual_place(String actual_place) {
        this.actual_place = actual_place;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Set<User> getFriends() {
        return Friends;
    }

    public void setFriends(Set<User> friends) {
        Friends = friends;
    }

    public Set<image> getAlbun() {
        return Albun;
    }

    public void setAlbun(Set<image> albun) {
        Albun = albun;
    }

    public Set<Notification> getNews() {
        return news;
    }

    public void setNews(Set<Notification> news) {
        this.news = news;
    }
}

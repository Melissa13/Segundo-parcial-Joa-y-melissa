package Clases;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Notification implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    private User owner;
    @OneToOne()
    private Post post;
    @OneToOne()
    private image imagen;
    @OneToOne()
    private User origen;
    private String mensaje;

    public Notification(User owner, Post post, image imagen, User origen, String mensaje) {
        this.owner = owner;
        this.post = post;
        this.imagen = imagen;
        this.origen = origen;
        this.mensaje = mensaje;
    }

    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public image getImagen() {
        return imagen;
    }

    public void setImagen(image imagen) {
        this.imagen = imagen;
    }

    public User getOrigen() {
        return origen;
    }

    public void setOrigen(User origen) {
        this.origen = origen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String tipo(){
        String tipo="";
        if(getPost()!=null){
            tipo="Post";
        }
        else if(getImagen()!=null){
            tipo="imagen";
        }
        else {tipo="amistad";}
        return  tipo;
    }

    public boolean amistad(){
        if(getMensaje().equals("Este usuario quiere ser su amigo")){
            return true;
        }
        return false;
    }

    public boolean post(){
        if(getMensaje().equals("Este usuario te etiqueto en su Post")){
            return true;
        }
        return false;
    }

    public boolean imagen(){
        if(getMensaje().equals("Este usuario te etiqueto en su imagen")){
            return true;
        }
        return false;
    }

    public boolean rechazo(){
        if(getMensaje().equals("Este usuario rechazo tu solicitud")){
            return true;
        }
        return false;
    }

    public boolean enemistad(){
        if(getMensaje().equals("Este usuario ha dejado de ser tu amigo")){
            return true;
        }
        return false;
    }

    public boolean aceptar(){
        if(getMensaje().equals("Este usuario ha aceptado su solicitud de amistad")){
            return true;
        }
        return false;
    }

    public boolean dioLike(){
        if(getMensaje().equals("Este usuario ha dado like en un post tuyo")){
            return true;
        }
        return false;
    }

    public boolean comento(){
        if(getMensaje().equals("Este usuario ha comentado en un post tuyo")){
            return true;
        }
        return false;
    }
}

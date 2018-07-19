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
}

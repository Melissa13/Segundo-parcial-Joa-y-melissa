package Clases;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class image implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private byte[] Image;
    private String direccion;
    private String body;
    @ManyToOne()
    private User Author;
    private Date DateTime;
    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER)
    private Set<Comment> commentsi;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> UserTagsi;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Likes> likesi;

    public image(byte[] image, String body, String direccion, User author, Date dateTime, Set<Comment> commentsi, Set<User> userTagsi, Set<Likes> likesi) {
        Image = image;
        this.direccion=direccion;
        this.body = body;
        Author = author;
        DateTime = dateTime;
        this.commentsi = commentsi;
        UserTagsi = userTagsi;
        this.likesi = likesi;
    }

    public image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return Author;
    }

    public void setAuthor(User author) {
        Author = author;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Set<Comment> getCommentsi() {
        return commentsi;
    }

    public void setCommentsi(Set<Comment> commentsi) {
        this.commentsi = commentsi;
    }

    public Set<User> getUserTagsi() {
        return UserTagsi;
    }

    public void setUserTagsi(Set<User> userTagsi) {
        UserTagsi = userTagsi;
    }

    public Set<Likes> getLikesi() {
        return likesi;
    }

    public void setLikesi(Set<Likes> likesi) {
        this.likesi = likesi;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

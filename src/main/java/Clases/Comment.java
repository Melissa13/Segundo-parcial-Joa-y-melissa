package Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    private User usuario;
    private String Text;
    private List<Tag> tags;
    @ManyToOne()
    private Post post;
    @ManyToOne()
    private image image;

    public Comment(User usuario, String text, List<Tag> tags, Post post, Clases.image image) {
        this.usuario = usuario;
        Text = text;
        this.tags = tags;
        this.post = post;
        this.image = image;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Clases.image getImage() {
        return image;
    }

    public void setImage(Clases.image image) {
        this.image = image;
    }
}

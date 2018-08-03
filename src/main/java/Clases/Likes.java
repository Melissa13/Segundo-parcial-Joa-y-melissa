package Clases;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({@NamedQuery(name = "Likes.findAllBylike", query = "select p from Likes  p where p.like.username like :user")})
public class Likes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    private User like;
    @ManyToOne()
    private Post postl;
    @ManyToOne()
    private image imagel;

    public Likes(User like, Post postl, image imagel) {
        this.like = like;
        this.postl = postl;
        this.imagel = imagel;
    }

    public Likes() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getLike() {
        return like;
    }

    public void setLike(User like) {
        this.like = like;
    }

    public Post getPostl() {
        return postl;
    }

    public void setPostl(Post postl) {
        this.postl = postl;
    }

    public image getImagel() {
        return imagel;
    }

    public void setImagel(image imagel) {
        this.imagel = imagel;
    }
}

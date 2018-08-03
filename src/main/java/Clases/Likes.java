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

    public Likes(User like) {
        this.like = like;
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
}

package Clases;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Likes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne()
    private User like;

    public Likes(User like) {
        this.like = like;
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

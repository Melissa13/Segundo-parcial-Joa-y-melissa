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
    private String body;
    @ManyToOne()
    private User Author;
    private Date DateTime;
    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER)
    private Set<Comment> commentsi;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> UserTagsi;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> likesi;
}

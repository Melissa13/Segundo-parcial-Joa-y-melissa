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
}

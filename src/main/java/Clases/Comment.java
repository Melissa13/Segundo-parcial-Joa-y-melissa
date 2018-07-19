package Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String Text;
    private List<Tag> tags;
    @ManyToOne()
    private Post post;
    @ManyToOne()
    private image image;



}

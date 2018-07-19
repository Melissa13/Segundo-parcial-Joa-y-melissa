package Clases;

import java.util.ArrayList;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Comment implements Serializable{

    private String username;
    private String Text;
    private List<Tag> tags;
    @ManyToOne()
    private Post post;
    @ManyToOne()
    private image image;



}

package Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Post.findAllById", query = "select p from Post  p ORDER BY p.id DESC ")})
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Title;
    private String Body;
    private image Image;
    private User Author;
    private Date DateTime;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;
    private List<User> UserTags;
    private List<Like> likes;


}

package Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "Post.findAllById", query = "select p from Post  p ORDER BY p.id DESC ")})
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Title; //listo
    private String Body;  //listo
    private String Image;  //listo
    @OneToOne()
    private User Authorp;  //listo
    private Date DateTime; //listo
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Set<Comment> comments;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> UserTags;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Likes> likes;

    public Post(String title, String body, String image, User authorp, Date dateTime, Set<Comment> comments, Set<Tag> tags, Set<User> userTags, Set<Likes> likes) {
        Title = title;
        Body = body;
        Image = image;
        Authorp = authorp;
        DateTime = dateTime;
        this.comments = comments;
        this.tags = tags;
        UserTags = userTags;
        this.likes = likes;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public User getAuthorp() {
        return Authorp;
    }

    public void setAuthorp(User authorp) {
        Authorp = authorp;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<User> getUserTags() {
        return UserTags;
    }

    public void setUserTags(Set<User> userTags) {
        UserTags = userTags;
    }

    public Set<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    public boolean validar(User u){

        for (Likes l:getLikes()){
            if(l.getLike().getUsername().equals(u.getUsername())){
                return true;
            }
        }
        return false;
    }
}

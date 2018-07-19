package Clases;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({@NamedQuery(name = "User.findAllByUsername", query = "select p from User  p where p.username like :username")})
public class User implements Serializable{
    @Id
    private String username;
    private String nombre;
    private Date date_birth;
    private String place_birth;
    private List<String> Studies;
    private String workplace;
    private boolean Administrador;
    private String actual_place;
    private String job;
    private Set<User> Friends;
    private Set<image> Albun;
    private Set<Notification> news;

}

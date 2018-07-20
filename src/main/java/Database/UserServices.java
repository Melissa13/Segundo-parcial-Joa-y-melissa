package Database;

import Clases.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServices extends DataBaseService<User>{

    private static UserServices instancia;

    private UserServices() {
        super(User.class);
    }

    public static UserServices getInstancia(){
        if(instancia==null){
            instancia = new UserServices();
        }
        return instancia;
    }

    /**
     *
     * @param username
     * @return
     */

    public List<User> findAllByUsername(String username){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("User.findAllByUsername");
        query.setParameter("username", "%"+username+"%");
        List<User> lista = query.getResultList();
        return lista;
    }
}


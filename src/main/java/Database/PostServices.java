package Database;

import Clases.Post;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PostServices extends DataBaseService<Post>{

    private static PostServices instancia;

    private PostServices() {
        super(Post.class);
    }

    public static PostServices getInstancia(){
        if(instancia==null){
            instancia = new PostServices();
        }
        return instancia;
    }

    /**
     *
     * @param id
     * @return
     */

    public List<Post> findAllById(){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Post.findAllById");
        //query.setParameter("username", "%"+username+"%");
        List<Post> lista = query.getResultList();
        return lista;
    }
}

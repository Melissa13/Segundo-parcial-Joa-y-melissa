package Database;

import Clases.Likes;
import Clases.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LikeService extends DataBaseService<Likes>{
    private static LikeService instancia;

    private LikeService() {
        super(Likes.class);
    }

    public static LikeService getInstancia(){
        if(instancia==null){
            instancia = new LikeService();
        }
        return instancia;
    }

    /**
     *
     * @param like
     * @return
     */
    public List<Likes> findAllBylike(User user){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Likes.findAllBylike");
        query.setParameter("user", "%"+user.getUsername()+"%");
        List<Likes> lista = query.getResultList();
        return lista;
    }
}

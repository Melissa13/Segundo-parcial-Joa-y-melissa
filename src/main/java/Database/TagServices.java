package Database;

import Clases.Tag;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TagServices extends DataBaseService<Tag>{

    private static TagServices instancia;

    private TagServices() {
        super(Tag.class);
    }

    public static TagServices getInstancia(){
        if(instancia==null){
            instancia = new TagServices();
        }
        return instancia;
    }

    /**
     *
     * @param tag
     * @return
     */
    public List<Tag> findAllBytag(String tag){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Tag.findAllBytag");
        query.setParameter("tag", "%"+tag+"%");
        List<Tag> lista = query.getResultList();
        return lista;
    }
}

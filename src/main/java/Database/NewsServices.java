package Database;

import Clases.Notification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class NewsServices extends DataBaseService<Notification>{

    private static NewsServices instancia;

    private NewsServices() {
        super(Notification.class);
    }

    public static NewsServices getInstancia(){
        if(instancia==null){
            instancia = new NewsServices();
        }
        return instancia;
    }

    /**
     *
     * @param productid
     * @return
     */
}

package Database;

import Clases.image;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ImageServices extends DataBaseService<image>{

    private static ImageServices instancia;

    private ImageServices() {
        super(image.class);
    }

    public static ImageServices getInstancia(){
        if(instancia==null){
            instancia = new ImageServices();
        }
        return instancia;
    }

    /**
     *
     * @param productid
     * @return
     */
}

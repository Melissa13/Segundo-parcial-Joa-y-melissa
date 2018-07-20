package Database;

import Clases.Comment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class CommentServices extends DataBaseService<Comment> {

    private static CommentServices instancia;

    private CommentServices() {
        super(Comment.class);
    }

    public static CommentServices getInstancia(){
        if(instancia==null){
            instancia = new CommentServices();
        }
        return instancia;
    }

    /**
     *
     * @param productid
     * @return
     */



}

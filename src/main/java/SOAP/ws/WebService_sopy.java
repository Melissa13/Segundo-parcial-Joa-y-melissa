package SOAP.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Clases.*;
import Database.PostServices;
import Database.UserServices;

@WebService
public class WebService_sopy {


    List<Post> postList = PostServices.getInstancia().findAll();
    List<User> userList = UserServices.getInstancia().findAll();

    @WebMethod
    public ArrayList<String> getUserPosts(String username)
    {
        ArrayList<String> postsUser = new ArrayList<String>();
        postsUser = main.main.Posteo(username);
        /*User temp = new User();
        for (User user:userList) {
            if (user.getUsername().equalsIgnoreCase(username))
                temp=user;
        }
        System.out.println(temp.getUsername());

        for (Post post: postList) {
            if (post.getAuthorp()==temp){
                postsUser.add(post.getTitle()+" "+post.getAuthorp().getUsername());
            }
        }*/
        System.out.println(postsUser);
        return postsUser;
    }

    @WebMethod
    public void Postear(String title, String body, String image, String username)
    {
        User temp = new User();
        for (User user:userList) {
            if (user.getUsername().equalsIgnoreCase(username))
                temp=user;
        }
        System.out.println(temp.getUsername());
        Date today = Calendar.getInstance().getTime();
        Post post = new Post();
        System.out.println(post.getId());
        post.setTitle(title);
        post.setBody(body);
        post.setImage(image);
        post.setAuthorp(temp);
        post.setDateTime(today);
        System.out.println(post.getBody()+" "+post.getImage());
        PostServices.getInstancia().crear(post);
    }



}

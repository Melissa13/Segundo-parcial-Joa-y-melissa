package REST;

import Clases.Post;
import Clases.User;
import Database.PostServices;
import Database.UserServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WebService_resty {

    List<Post> postList = PostServices.getInstancia().findAll();
    List<User> userList = UserServices.getInstancia().findAll();


    public ArrayList<String> getUserPosts(String username)
    {
        ArrayList<String> postsUser = new ArrayList<String>();
        postsUser = main.main.Posteo(username);

        System.out.println(postsUser);
        return postsUser;
    }

    public Post Postear(Post post,String username)
    {
        User user = new User();
        for (User tmp: userList) {
            if (tmp.getUsername().equalsIgnoreCase(username))
                user = tmp;
        }
        Date today = Calendar.getInstance().getTime();
        post.setAuthorp(user);
        post.setDateTime(today);
        System.out.println(post.getTitle());
      PostServices.getInstancia().crear(post);
      return post;
    }

}

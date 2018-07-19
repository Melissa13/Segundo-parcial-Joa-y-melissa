package Clases;

import java.util.ArrayList;

public class Post {

    private String Text;
    private String username;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;

    public Post(String text, String username, ArrayList<Comment> comments, ArrayList<Tag> tags) {
        Text = text;
        this.username = username;
        this.comments = comments;
        this.tags = tags;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}

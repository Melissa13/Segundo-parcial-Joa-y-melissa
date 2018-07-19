package Clases;

import java.util.ArrayList;

public class Comment {

    private String username;
    private String Text;
    private ArrayList<Tag> tags;

    public Comment(String username, String text, ArrayList<Tag> tags) {
        this.username = username;
        Text = text;
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}

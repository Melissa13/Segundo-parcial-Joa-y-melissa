package Clases;

public class Tag {

    private int tag_id;
    private String username;

    public Tag(int tag_id, String username) {
        this.tag_id = tag_id;
        this.username = username;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

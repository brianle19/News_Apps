package thaile.com.aiw_client_finalproject;

/**
 * Created by Thai Le on 11/30/2016.
 */

public class CommentObj {
    private String username;
    private String content;
    private String datetime;

    public CommentObj(String username, String content, String datetime) {
        this.username = username;
        this.content = content;
        this.datetime = datetime;
    }

    public String getUsername() {
        return username;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getContent() {
        return content;
    }

}

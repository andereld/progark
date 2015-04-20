package json;

/**
 * Created by mats on 20.04.15.
 */
public class JsonData {

    private String username;

    public JsonData() {}

    public JsonData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

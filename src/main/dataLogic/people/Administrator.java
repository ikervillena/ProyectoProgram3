package main.dataLogic.people;

/**Represents an administrator who updates the information regarding rounds and seasons.
 * @author Iker Villena Ona.
 */

public class Administrator {

    String username;
    String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

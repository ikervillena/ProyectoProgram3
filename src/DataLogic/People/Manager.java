package DataLogic.People;

/** Represents a user of the game, a manager.
 * @author Iker Villena Ona.
 */

public class Manager {

    String username;
    String password;

    /**Gets the username of the manager.
     * @return String with the username.
     */

    public String getUsername() {
        return username;
    }

    /**Sets a new username for the manager.
     * @param username.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**Gets the password of the manager.
     * @return String with the password.
     */

    public String getPassword() {
        return password;
    }

    /**Sets a new password for the manager.
     * @param password.
     */

    public void setPassword(String password) {
        this.password = password;
    }
}

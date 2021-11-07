package DataLogic.People;

import DBManagement.DataExtractor;

/** Represents a user of the game, a manager.
 * @author Iker Villena Ona.
 */

public class Manager {

    String username;
    String password;
    String name;
    String surname;

    // Falta documentar el constructor

    public Manager(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    /**This method checks whether the username and password provided belong to any of the managers
     * @param username
     * @param password
     * @return a boolean which is true if the username and the password are correct, and false if they are not.
     */

    public static boolean correctPassword(String username, String password){
        boolean correctPassword = false;
        for(Manager m : DataExtractor.getManagers()){
            if(m.getUsername().equals(username) && m.getPassword().equals(password)){
                correctPassword = true;
                break;
            }
        }
        return correctPassword;
    }

    /**Gets the username of the manager.
     * @return String with the username.
     */

    public String getUsername() {
        return username;
    }

    /**Sets a new username for the manager.
     * @param username
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
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }
}

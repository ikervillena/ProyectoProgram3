package main.dataLogic.people;

/**Represents a user of the game.
 * @author Iker Villena Ona.
 */

public class User {
    String username;
    String password;

    public boolean checkPassword(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        } else{
            return false;
        }
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**Provides the text that needs to be shown to the User when he has correctly logged in.
     * @return String with the log in text.
     */

    public String getLoginText(){
        return "Contraseña correcta, bienvenido.";
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

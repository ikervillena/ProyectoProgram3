package main.dataLogic.people;

/**Represents an administrator who updates the information regarding rounds and seasons.
 * @author Iker Villena Ona.
 */

public class Administrator extends User{

    boolean fullAccess;

    public Administrator(String username, String password, boolean fullAccess) {
        super(username, password);
        this.fullAccess = fullAccess;
    }

    /**Provides the text that needs to be shown to the Administrator when he has correctly logged in.
     * @return String with the log in text.
     */

    @Override
    public String getLoginText() {
        if(fullAccess){
            return "Contraseña correcta. Bienvenido, tienes acceso completo.";
        } else{
            return "Contraseña correcta. Bienvenido, tienes acceso limitado.";
        }
    }

    public boolean isFullAccess() {
        return fullAccess;
    }
}

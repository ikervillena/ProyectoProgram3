package main.businessLogic.interfaces;

/**Contains the methods that allow to connect the DataBase and the data logic.
 * @author Iker Villena Ona
 */

public interface IDBConnection {

    /**Provides an object's ID number on the DataBae.
     * @return Integer with the ID number.
     */

    int getID();
}

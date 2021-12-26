package main.businessLogic.interfaces;

/**Contains the methods needed to check the data before inserting it to the Database.
 * @author Iker Villena Ona
 */

public interface INewData {

    /**Checks the fields that contain data that is going to be saved to the DataBase.
     * @return Boolean with the value true if the fields are OK and false if they are not.
     */

    boolean checkFields();

}

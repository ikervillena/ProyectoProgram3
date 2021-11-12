package main.dbManagement;

/** This class contains methods related to the Database that can be useful in other classes.
 * @author Iker Villena Ona
 */

public class DBUtils {

    /**Provides an ID number that is not used yet.
     * @param table a table's name.
     * @param column a table's column's name
     * @return an ID number that is not registered in the table's column.
     */

    public static int generateID(String table,String column){
        int newID = 0;
        while(!DataValidation.check(table,column,newID)){
            newID = newID + 1;
        }
        return newID;
    }

}

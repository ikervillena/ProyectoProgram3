package dbManagement;

public class DBUtils {

    public static int generateID(String table,String column){
        int newID = 0;
        while(!DataValidation.check(table,column,newID)){
            newID = newID + 1;
        }
        return newID;
    }

}

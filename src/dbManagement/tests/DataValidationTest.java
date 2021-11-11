package dbManagement.tests;

import dbManagement.DataValidation;
import org.junit.Test;

public class DataValidationTest {

    @Test
    public void checkEntryCode(){
        System.out.println(DataValidation.checkEntryCode("no existe"));
        System.out.println(DataValidation.checkEntryCode("new League"));
    }

}

package test.businessLogic;

import main.businessLogic.MergeSort;
import main.businessLogic.TacticalFormation;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class MergeSortTest {

    private ArrayList<TacticalFormation> formationsList;

    @Before
    public void setUp(){
        formationsList = DataExtraction.getAllFormations();
    }

    /**Tests the MergeSort() method.
     */

    @Test
    public void MergeSort(){
        MergeSort myMergeSort = new MergeSort<TacticalFormation>();
        myMergeSort.mergeSort(formationsList);
        for(int i = 0; i < formationsList.size()-1; i++){
            if(formationsList.get(i).compareTo(formationsList.get(i+1)) == 1){
                fail("The list of formations is not properly sorted.");
            }
        }
    }

}

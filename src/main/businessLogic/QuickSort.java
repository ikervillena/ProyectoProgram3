package main.businessLogic;

import main.businessLogic.interfaces.IComparable;
import java.util.ArrayList;
import java.util.Collections;

/**This class is aimed at ordering an ArrayList of objects that implement the interface IComparable, using
 * the Quick Sort algorithm.
 * @author Iker Villena Ona
 */

public class QuickSort <T extends IComparable>{

    /**Partition method used for quicksort algorithm.
     * @param list ArrayList<T> list of objects that needs to be sorted.
     * @param low Integer that references a list's index from the left side.
     * @param high Integer that references a list's index from the right side.
     * @return Integer with the pivot's location (index of the list).
     */

    private int partition(ArrayList<T> list, int low, int high) {
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (list.get(j).compareTo(list.get(high))<0) {
                i++;
                Collections.swap(list,i,j);
            }
        }
        Collections.swap(list,i+1,high);
        return i+1;
    }

    /**Sorts an ArrayList.
     * @param list ArrayList<T> that needs to be sorted.
     * @param low Integer that references a list's index (left border).
     * @param high Integer that references a list's index (right border).
     */

    public void sort(ArrayList<T> list, int low, int high){
        if(low<high){
            int pivotLocation = partition(list,low,high);
            sort(list,low,pivotLocation-1);
            sort(list,pivotLocation+1,high);
        }
    }

}

package main.businessLogic;

import main.businessLogic.interfaces.IComparable;

import java.util.ArrayList;

public class MergeSort <T extends IComparable>{

    /**Merge method used in the merge sort algorithm.
     * @param array ArrayList<T> that needs to be sorted.
     * @param left ArrayList<T> with the values of the left side of the array that needs to be sorted.
     * @param right ArrayList<T> with the values of the right side of the array that needs to be sorted.
     */

    private void merge(ArrayList<T> array, ArrayList<T> left, ArrayList<T> right){
        //set up a temporary arraylist to build the merge list
        ArrayList<T> temp = new ArrayList<>();

        //set up index values for merging the two lists
        int numbersIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) == -1 ) {
                array.set(numbersIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                array.set(numbersIndex, right.get(rightIndex));
                rightIndex++;
            }
            numbersIndex++;
        }

        int tempIndex = 0;
        if (leftIndex >= left.size()) {
            temp = right;
            tempIndex = rightIndex;
        }
        else {
            temp = left;
            tempIndex = leftIndex;
        }

        for (int i = tempIndex; i < temp.size(); i++) {
            array.set(numbersIndex, temp.get(i));
            numbersIndex++;
        }
    }

    /**Implementation of the merge sort algorithm that orders ArrayLists of different type of Objects that belong to
     * classes implementing the interface "IComparable.java" .
     * @param array ArrayList<T> that needs to be sorted.
     */

    public void mergeSort(ArrayList<T> array) {
        int middle;
        ArrayList<T> left = new ArrayList<>();
        ArrayList<T> right = new ArrayList<>();
        if (array.size() > 1) {
            middle = array.size() / 2;
            for (int i = 0; i < middle; i++)
                left.add(array.get(i));
            for (int j = middle; j < array.size(); j++)
                right.add(array.get(j));

            mergeSort(left);
            mergeSort(right);
            merge(array, left, right);
        }
    }
}


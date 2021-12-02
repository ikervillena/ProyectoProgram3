package main.businessLogic;

import main.dataLogic.people.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Algorithm {

    /**Partition method used for quicksort algorithm.
     * @param playersList ArrayList<Player> list of players that needs to be sorted.
     * @param low Integer that references a list's index from the left side.
     * @param high Integer that references a list's index from the right side.
     * @return Integer with the pivot's location (index of the list).
     */

    private static int partition(ArrayList<Player> playersList, int low, int high) {
        int pi = playersList.get(high).getPosition().getIndex();
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (playersList.get(j).getPosition().getIndex() <= pi) {
                i++;
                Collections.swap(playersList,i,j);
            }
        }
        Collections.swap(playersList,i+1,high);
        return i+1;
    }

    /**Orders a list of players based on their position. Following the next order: Goalkeeper - Defense - Midfielder - Forward.
     * @param playersList ArrayList<Player> that must be sorted.
     * @param low Integer that references a list's index (left border).
     * @param high Integer that references a list's index (right border).
     */

    public static void quickSortPlayers(ArrayList<Player> playersList, int low, int high){
        if(low<high){
            int pivotLocation = partition(playersList,low,high);
            quickSortPlayers(playersList,low,pivotLocation-1);
            quickSortPlayers(playersList,pivotLocation+1,high);
        }
    }

    public static void mergeSort(String[] names) {
        if (names.length >= 2) {
            String[] left = new String[names.length / 2];
            String[] right = new String[names.length - names.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = names[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = names[i + names.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(names, left, right);
        }
    }

    public static void merge(String[] names, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                names[i] = left[a];
                a++;
            } else {
                names[i] = right[b];
                b++;
            }
        }
    }

}
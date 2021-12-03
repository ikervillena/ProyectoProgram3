package main.businessLogic.interfaces;

public interface IComparable <T>{

    /**Compares two objects.
     * @param object Object that needs to be compared.
     * @return Integer with the value if this object is bigger, -1 if it is smaller and 0 if they are equals.
     */

    int compareTo(T object);

}

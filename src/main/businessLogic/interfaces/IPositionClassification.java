package main.businessLogic.interfaces;

import main.dataLogic.people.Player;

import java.util.ArrayList;

public interface IPositionClassification {

    ArrayList<Player> getDefenders();
    ArrayList<Player> getMidfielders();
    ArrayList<Player> getForwards();

}

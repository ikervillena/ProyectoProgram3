package main.dataLogic.people.attributes;

import main.dbManagement.DataExtraction;
import java.util.ArrayList;

/**Represents a position in which a player usually plays: goalkeeper/defender/midfielder/forward.
 * @author Iker Villena Ona.
 */

public class Position {
    String name;
    String shortName;
    int pointsForGoal;
    int pointsForAssist;
    int pointsForNoGoalsAgainst;
    int pointsForGoalsAgainst;

    public Position(String name, String shortName, int pointsForGoal, int pointsForAssist, int pointsForNoGoalsAgainst, int pointsForGoalsAgainst) {
        this.name = name;
        this.shortName = shortName;
        this.pointsForGoal = pointsForGoal;
        this.pointsForAssist = pointsForAssist;
        this.pointsForNoGoalsAgainst = pointsForNoGoalsAgainst;
        this.pointsForGoalsAgainst = pointsForGoalsAgainst;
    }

    /**It provides a Position whose name is the one provided as a parameter.
     * @param positionName the name of a position.
     * @return A position with the same name as the one provided as a parameter.
     */

    public static Position getPosition(String positionName){
        ArrayList<Position> positionsList = DataExtraction.getPositions();
        Position position = null;
        for (Position p : positionsList){
            if(p.getName().equals(positionName)){
                position = p;
            }
        }
        return position;
    }

    /**Provides the position's index for ordering a positions list.
     * @return Integer with the index of a position in a position's list.
     */

    public int getIndex(){
        switch(name){
            case "Goalkeeper":
                return 1;
            case "Defense":
                return 2;
            case "Midfielder":
                return 3;
            case "Forward":
                return 4;
            default:
                return 5;
        }
    }

    /**Provides the Position's name in spanish.
     * @return String with the Position's name in spanish.
     */

    public String toSpanish(){
        switch(this.name){
            case "Goalkeeper":
                return "Portero";
            case "Defense":
                return "Defensa";
            case "Midfielder":
                return "Centrocampista";
            case "Forward":
                return "Delantero";
            default:
                return "Sin definir";
        }
    }

    @Override
    public String toString() {
        return shortName;
    }

    public String getName() {return name;}

    public String getShortName() {
        return shortName;
    }

    public int getPointsForGoal() {
        return pointsForGoal;
    }

    public int getPointsForAssist() {
        return pointsForAssist;
    }

    public int getPointsForNoGoalsAgainst() {
        return pointsForNoGoalsAgainst;
    }

    public int getPointsForGoalsAgainst() {
        return pointsForGoalsAgainst;
    }
}

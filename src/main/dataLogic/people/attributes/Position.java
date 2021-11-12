package main.dataLogic.people.attributes;

import main.dbManagement.DataExtraction;

import java.util.ArrayList;

/**Represents a position in which a player usuarlly plays: goalkeeper/defender/midfielder/forward.
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

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", pointsForGoal=" + pointsForGoal +
                ", pointsForAssist=" + pointsForAssist +
                ", pointsForNoGoalsAgainst=" + pointsForNoGoalsAgainst +
                ", pointsForGoalsAgainst=" + pointsForGoalsAgainst +
                '}';
    }

    public String getName() {return name;}

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

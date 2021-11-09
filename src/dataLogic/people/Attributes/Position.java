package dataLogic.people.Attributes;

import dbManagement.DataExtraction;

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

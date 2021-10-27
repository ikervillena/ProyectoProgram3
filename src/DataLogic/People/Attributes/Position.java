package DataLogic.People.Attributes;

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

    public String getName() {
        return name;
    }

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

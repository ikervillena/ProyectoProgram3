package DataLogic.People.Attributes;

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

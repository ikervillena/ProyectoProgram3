package main.businessLogic;

import main.dataLogic.people.attributes.Position;

/** Represents the statistics for a player in a concrete match: if he has played, scored goals, assists...
 * @author Iker Villena Ona
 */

public class Statistic {

    boolean played;
    int numGoals;
    int numAssists;
    int receivedGoals;
    int yellowCards;
    boolean redCard;

    public Statistic(boolean played, int numGoals, int numAssists, int receivedGoals, int yellowCards, boolean redCard) {
        this.played = played;
        this.numGoals = numGoals;
        this.numAssists = numAssists;
        this.receivedGoals = receivedGoals;
        this.yellowCards = yellowCards;
        this.redCard = redCard;
    }

    /** Gets the total points obtained by a player in a match based on his statistics.
      * @param position The position in which a player usually plays.
     * @return an integer representing total the points obtained by a player in a match.
     */

    public int getPoints(Position position){
        int points = 0;
        if (played){
            points = 2;
            points = points + numGoals * position.getPointsForGoal() + numAssists * position.getPointsForAssist()
            + yellowCards * (-1);
            if(receivedGoals == 0){
                points = points + position.getPointsForNoGoalsAgainst();
            } else{
                points = points + receivedGoals * position.getPointsForGoalsAgainst();
            }
            if(redCard){
                points = points - 2;
            }
        }
        return points;
    }

    public boolean isPlayed() {
        return played;
    }

    public int getNumGoals() {
        return numGoals;
    }

    public int getNumAssists() {
        return numAssists;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public boolean isRedCard() {
        return redCard;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "played=" + played +
                ", numGoals=" + numGoals +
                ", numAssists=" + numAssists +
                ", receivedGoals=" + receivedGoals +
                ", yellowCards=" + yellowCards +
                ", redCard=" + redCard +
                '}';
    }
}

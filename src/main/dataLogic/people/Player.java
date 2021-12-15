package main.dataLogic.people;

import main.businessLogic.Statistic;
import main.businessLogic.interfaces.IDBConnection;
import main.dataLogic.league.Club;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.attributes.Position;
import main.dbManagement.DataExtraction;

import java.util.ArrayList;

/** Represents a football player.
 * @author Iker Villena Ona.
 */

public class Player implements IDBConnection {

    String name;
    String surname;
    String shirtName;
    int shirtNumber;
    Position position;
    float[] valueHistory;
    ArrayList<Statistic> statsRecord;

    /**Constructor of a Player.
     * @param name A String with the player's name.
     * @param surname A String with the player's surname.
     * @param shirtName A String with the player's shirt name.
     * @param shirtNumber An integer with the player's shirt number.
     * @param position A Position in which the player usually plays.
     * @param valueHistory A float[] containing the player's valuer history.
     * @param statsRecord An ArrayList<Statistic> that contains all the statistics of the player.
     */

    public Player(String name, String surname, String shirtName, int shirtNumber, Position position, float[] valueHistory, ArrayList<Statistic> statsRecord) {
        this.name = name;
        this.surname = surname;
        this.shirtName = shirtName;
        this.shirtNumber = shirtNumber;
        this.position = position;
        this.valueHistory = valueHistory;
        this.statsRecord = statsRecord;
    }

    /** Constructor used for testing the class "Squad.java".
     */

    public Player() {
    }

    /**provides the club for which the player plays.
     * @return The club where the player plays.
     */

    public Club getClub(){
        Club club = null;
        for(Club c : DataExtraction.getAllClubs()){
            for(Player p : c.getPlayersList()){
                if(p.getShirtName().equals(shirtName)){
                    club = c;
                    break;
                }
            }
        }
        return club;
    }

    /**Provides a list with the points obtained by the player for each of the rounds of the league.
     * @return An ArrayList<Integer> with the list of points.
     */

    public ArrayList<Integer> getPointsRecord(){
        ArrayList<Integer> pointsRecord = new ArrayList<>();
        for(Statistic s : statsRecord){
            pointsRecord.add(s.getPoints(position));
        }
        return pointsRecord;
    }

    /**Provides the total points obtained by a player in a specific round of the league.
     * @param roundNum Integer with the round number.
     * @return Integer with the number of points of player obtained by the player.
     */

    public int getPoints(int roundNum){
        if(roundNum > 0 && roundNum <= getPointsRecord().size()){
            return getPointsRecord().get(roundNum-1);
        } else{
            return 0;
        }
    }

    /**Provides the ID number with which the Player is registered in the Database.
     * @return An integer with the ID number.
     */

    @Override
    public int getID() {
        return DataExtraction.getID("player","player_id","shirtname",shirtName);
    }

    /**This method transforms the object Player into a String.
     * @return a String with the player's name, surname, shirt name, shirt number, position and valuer history.
     */

    @Override
    public String toString() {
        return shirtName+" ["+shirtNumber+"]";
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj instanceof Player) {
            Player p = (Player) obj;
            if (p.getName().equals(this.name) && p.getSurname().equals(this.surname) && p.getShirtName().equals(this.shirtName)
                    && p.getShirtNumber() == this.shirtNumber) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    /**Provides the player's last value saved
     * @return Float with the last value saved to the DataBase.
     */

    public float getLastValue(){
        float lastValue = 0;
        for(int i = 0; i<this.valueHistory.length; i++){
            if(valueHistory[i] > 0){
                lastValue = valueHistory[i];
            }
        }
        return lastValue;
    }

    /**Provides the new value of a player after playing a new round of the league, based on his statistics.
     * @param points Integer with the number of points obtained in the round.
     * @return float with the player's new value.
     */

    public float getNewValue(int points){
        float lastValue = getLastValue();
        if(points<0){
            lastValue*=0.6;
        } else{
            if(points<4){
                lastValue*=0.75;
            } else{
                if(points>11){
                    lastValue*=1.35;
                } else{
                    if(points>5){
                        lastValue*=1.2;
                    }
                }
            }
        }
        return lastValue;
    }

    /**Provides the team for which the player plays in a specific league.
     * @param league League in which the player must be searched.
     * @return Team in which the player plays, it has the value null if the player is free.
     */

    public Team getTeam(League league){
        Team team = null;
        for(Team t : league.getTeamsList()){
            for(Player p : t.getPlayersList()){
                if(p.equals(this)){
                    team = t;
                }
            }
        }
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public float[] getValueHistory() {
        return valueHistory;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getShirtName() {
        return shirtName;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public ArrayList<Statistic> getStatsRecord() {
        return statsRecord;
    }

}
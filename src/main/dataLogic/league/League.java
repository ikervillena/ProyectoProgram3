package main.dataLogic.league;

import main.dataLogic.interfaces.IDBConnection;
import main.dataLogic.people.Player;
import main.dbManagement.DBUtils;
import main.dbManagement.DataExtraction;
import main.dataLogic.people.Manager;

import java.util.ArrayList;

/** Represents a league where teams compete.
 * @author Iker Villena Ona.
 */

public class League implements IDBConnection {

    String name;
    String entryCode;
    ArrayList<Team> teamsList;

    /**This method is a constructor of a League.
     * @param name A String with the name of the league.
     * @param entryCode A String with the code needed to join the league.
     * @param teamsList An ArrayList containing the list of teams participating in the league.
     */

    public League(String name, String entryCode, ArrayList<Team> teamsList) {
        this.name = name;
        this.entryCode = entryCode;
        this.teamsList = teamsList;
    }

    /**A constructor for testing the class in "LeagueTest.java".
     * @param teamsList
     */

    public League(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    /**This method provides the ID number of the league.
     * For that: it uses the static method "getLeague()" of the class "DataExtractor.java", providing the entry code of the league as a parameter.
     * @return an integer with the ID number of the league.
     */

    @Override
    public int getID(){
       return DataExtraction.getLeagueID(entryCode);
    }

    /**This method provides a leagueID that is not registered in the Database.
     * For that: it increments the ID number until it finds one that is not registered (checked with the method checkLeagueID of DataExtraction).
     * @return an integer with the leagueID.
     */

    public static int generateID(){
        return DBUtils.generateID("league","league_id");
    }

    /**This method checks whether a specific manager has access to the league or not.
     * For that: it looks for the manager in each of the teams that form the league.
     * @param manager An object of the class Manager.
     * @return a boolean which has the value true if the manager has access and false if he does not.
     */

    public boolean canAccess(Manager manager){
        boolean canAccess = false;
        for(Team t : teamsList){
            if(t.getManager().getUsername().equals(manager.getUsername())){
                canAccess = true;
            }
        }
        return canAccess;
    }

    /**This method provides the players of the league that do not belong to any team yet.
     * @return an ArrayList with the list of free players.
     */

    public ArrayList<Player> getFreePlayers(){
        ArrayList<Player> freePlayers = DataExtraction.getAllPlayers();
        for (Team t : teamsList){
            for (Player p : t.getPlayersList()){
                for (Player player : freePlayers){
                    if(player.getID() == p.getID()){
                        freePlayers.remove(player);
                        break;
                    }
                }
            }
        }
        return freePlayers;
    }

    /**Adds a team to a league, including it in the list of teams.
     * @param newTeam A Team that is being added to the league's list of teams.
     */

    public void addTeam(Team newTeam){
        ArrayList<Team> list = teamsList;
        list.add(newTeam);
        setTeamsList(list);
    }

    /**Removes a team from a league, taking it off the list.
     * @param removedTeam A Team that is being removed from the league's list of teams.
     */

    public void removeTeam(Team removedTeam){
        ArrayList<Team> list = teamsList;
        list.remove(removedTeam);
        setTeamsList(list);
    }

    /**Gets the code needed for joining a league.
     * @return Entry code.
     */

    public String getEntryCode() {
        return entryCode;
    }

    /**Sets a new code for joining the league.
     * @param entryCode A String with the code needed to join the league.
     */

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    /**Gets the list of teams participating in the League.
     * @return ArrayList with the list of teams.
     */

    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }

    /**Sets the list of teams participating in the league.
     * @param teamsList An Arraylist with the Teams that form the league.
     */

    private void setTeamsList(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**This method transforms the object League into a String.
     * @return a String with the league's name.
     */

    @Override
    public String toString() {
        return name;
    }
}

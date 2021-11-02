package DataLogic.League;

import java.util.ArrayList;

/** Represents a league where teams compete.
 * @author Iker Villena Ona.
 */

public class League {

    String entryCode;
    ArrayList<Team> teamsList;

    public League(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    /**Adds a team to a league, including it in the list of teams.
     * @param newTeam
     */

    public void addTeam(Team newTeam){
        ArrayList<Team> list = teamsList;
        list.add(newTeam);
        setTeamsList(list);
    }

    /**Removes a team from a league, taking it off the list.
     * @param removedTeam
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
     * @param entryCode
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
     * @param teamsList
     */

    private void setTeamsList(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

}

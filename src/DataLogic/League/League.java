package DataLogic.League;

import java.util.ArrayList;

/** Represents a league where teams compete
 * @author Iker Villena Ona
 */

public class League {

    String entryCode;
    ArrayList<Team> teamsList;

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }

    public void setTeamsList(ArrayList<Team> teamsList) {
        this.teamsList = teamsList;
    }

    //private void addTeam(Team newTeam){
    //}
}

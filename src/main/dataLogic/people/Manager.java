package main.dataLogic.people;

import main.dataLogic.league.Team;
import main.dbManagement.DataExtraction;
import main.dataLogic.league.League;
import main.dbManagement.DataInsertion;
import main.dbManagement.DataValidation;

import java.util.ArrayList;

/** Represents a user of the game, a manager.
 * @author Iker Villena Ona.
 */

public class Manager {

    String username;
    String password;
    String name;
    String surname;

    /**A Manager constructor.
     * @param username A String with the manager's username.
     * @param password A String with the manager's password to log in.
     * @param name A String with the manager's name.
     * @param surname A String with the manager's surname.
     */

    public Manager(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    /**This method creates a new league and saves the data in the Database.
     * @param leagueName A String with the new league's name.
     * @param entryCode A String with the new league's entry code.
     */

    public void createLeague(String leagueName, String entryCode){
        ArrayList<Team> teamsList = new ArrayList<>();
        teamsList.add(new Team(this));
        League league = new League(leagueName,entryCode,teamsList);
        DataInsertion.newLeague(league);
    }

    /**This method checks the entry code and, if it is correct, creates a new team for the manager and saves it to the Database.
     * @param entryCode A String with the entry code of the league that the manager is trying to join.
     */

    public void joinLeague(String entryCode){
        if(!alreadyInLeague(entryCode) && DataValidation.checkEntryCode(entryCode)){
            League league = DataExtraction.getLeague(entryCode);
            Team team = new Team(this,Team.generatePlayersList(league.getFreePlayers()));
            DataInsertion.insertTeam(team,league);
        }
    }

    /**This method checks whether the manager is already participating in the league referenced through the entry code or not.
     * @param entryCode String with the entry code of the league that is being checked.
     * @return a boolean which has the value true if the manager is already participating in the league, and false if he is not.
     */

    public boolean alreadyInLeague(String entryCode){
        boolean alreadyInLeague = false;
        for (League l : getLeagues()){
            if(DataValidation.checkEntryCode(entryCode) && l.getID() == DataExtraction.getLeague(entryCode).getID()){
                alreadyInLeague = true;
            }
        }
        return alreadyInLeague;
    }

    /**Provides the leagues in which a manager is participating.
     * @return an ArrayList with the leagues to which the manager has access.
     */

    public ArrayList<League> getLeagues(){
        ArrayList<League> leaguesList = new ArrayList<>();
        for(League l : DataExtraction.getAllLeagues()){
            if(l.canAccess(this)){
                leaguesList.add(l);
            }
        }
        return leaguesList;
    }

    /**Gets the username of the manager.
     * @return String with the username.
     */

    public String getUsername() {
        return username;
    }

    /**Sets a new username for the manager.
     * @param username the username for the manager.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**Gets the password of the manager.
     * @return String with the password.
     */

    public String getPassword() {
        return password;
    }

    /**Sets a new password for the manager.
     * @param password the password for the manager.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**This method transforms the object Manager into a String.
     * @return a String with the manager's username, password, name and surname.
     */

    @Override
    public String toString() {
        return "Manager{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

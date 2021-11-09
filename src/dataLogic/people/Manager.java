package dataLogic.people;

import dataLogic.league.Team;
import dbManagement.DataExtraction;
import dataLogic.league.League;
import dbManagement.DataInsertion;
import dbManagement.DataValidation;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;

/** Represents a user of the game, a manager.
 * @author Iker Villena Ona.
 */

public class Manager {

    String username;
    String password;
    String name;
    String surname;

    // Falta documentar el constructor

    public Manager(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    /**This method checks whether the username and password provided belong to any of the managers
     * @param username
     * @param password
     * @return a boolean which is true if the username and the password are correct, and false if they are not.
     */

    public static boolean correctPassword(String username, String password){
        boolean correctPassword = false;
        for(Manager m : DataExtraction.getAllManagers()){
            if(m.getUsername().equals(username) && m.getPassword().equals(password)){
                correctPassword = true;
                break;
            }
        }
        return correctPassword;
    }

    /**This method creates a new league and saves the data in the Database.
     * @param leagueName
     * @param entryCode
     */

    public void createLeague(String leagueName, String entryCode){
        ArrayList<Team> teamsList = new ArrayList<>();
        teamsList.add(new Team(this));
        League league = new League(leagueName,entryCode,teamsList);
        DataInsertion.newLeague(league);
    }

    /**This method checks the entry code and, if it is correct, creates a new team for the manager and saves it to the Database.
     * @param entryCode
     */

    public void joinLeague(String entryCode){
        if(DataValidation.checkEntryCode(entryCode)){
            League league = DataExtraction.getLeague(entryCode);
            Team team = new Team(this,Team.generatePlayersList(league.getFreePlayers()));
            DataInsertion.insertTeam(team,league);
        }
    }

    public ArrayList<League> getLeagues(){
        ArrayList<League> leaguesList = new ArrayList<>();
        for(League l : leaguesList){
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
     * @param username
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
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

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

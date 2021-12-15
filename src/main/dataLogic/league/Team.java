package main.dataLogic.league;

import main.businessLogic.interfaces.IDBConnection;
import main.businessLogic.interfaces.IPositionClassification;
import main.dbManagement.DBUtils;
import main.dbManagement.DataExtraction;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

/** Represents a virtual team that users of the game (managers) can manage.
 * @author Iker Villena Ona.
 */

public class Team implements IDBConnection, IPositionClassification {

    int id;
    Manager manager;
    ArrayList<Player> playersList;
    ArrayList<Squad> squadRecord = new ArrayList<>();

    public Team(int id, Manager manager, ArrayList<Player> playersList, ArrayList<Squad> squadRecord) {
        this.id = id;
        this.manager = manager;
        this.playersList = playersList;
        this.squadRecord = squadRecord;
    }

    /**Team constructor that has an empty squadRecord.
     * @param manager An object of the class Manager.
     * @param playersList An Arraylist with the list of players that form the team.
     */

    public Team(Manager manager, ArrayList<Player> playersList) {
        this.manager = manager;
        this.playersList = playersList;
    }

    /**Team constructor that has an empty squadRecord and that makes a playersList out of all the players of the league.
     * @param manager An object of the class Manager.
     */

    public Team(Manager manager) {
        this.manager = manager;
    }

    /**Provides a random list of players, with a minimum number of players that play in each position.
     * @param allPlayers An ArrayList with the list of players from which the players are chosen.
     * @return An ArrayList of players with a minimum number of players that play in each position.
     */
    //THE NUMBER OF PLAYERS PER POSITION MUST BE CHANGED when enough players are registered in the Database.
    public static ArrayList<Player> generatePlayersList(ArrayList<Player> allPlayers){
        ArrayList<Player> playersList = new ArrayList<>();
        boolean goalkeeper = false;
        int defenders = 0;
        int midfielders = 0;
        int forwards = 0;
        if(canGenerateTeam(playersList)){
            while(!goalkeeper || (defenders+midfielders+forwards) < 12){
                Player player = allPlayers.get((int) (Math.random()*allPlayers.size()));
                String positionName = player.getPosition().getName();
                if(positionName.equals("Goalkeeper") && !goalkeeper){
                    playersList.add(player);
                    goalkeeper = true;
                } else{
                    if(positionName.equals("Defense") && defenders < 4){
                        playersList.add(player);
                        defenders++;
                    } else{
                        if(positionName.equals("Midfielder") && midfielders < 4){
                            playersList.add(player);
                            midfielders++;
                        } else{
                            if(positionName.equals("Forward") && forwards < 4){
                                playersList.add(player);
                                forwards++;
                            }
                        }
                    }
                }
                allPlayers.remove(player);
            }
        }
        return playersList;
    }

    /**Checks if it is possible to generate a new team out of a list of players.
     * For that: it checks that there are at least 1 goalkeeper, 4 defenses, 4 midfielders and 4 forwards.
     * @param playersList
     * @return
     */

    public static boolean canGenerateTeam(ArrayList<Player> playersList){
        if(numPlayers("Goalkeeper",playersList)>=1&&numPlayers("Defense",playersList)>=4&&
        numPlayers("Midfielder",playersList)>=4&&numPlayers("Forward",playersList)>=4){
            return true;
        }else{
            return false;
        }
    }

    private static int numPlayers(String positionName, ArrayList<Player> playersList){
        int numPlayers = 0;
        for(Player p : playersList){
            if(p.getPosition().getName().equals(positionName)){
                numPlayers++;
            }
        }
        return numPlayers;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public Manager getManager() {
        return manager;
    }

    public ArrayList<Squad> getSquadRecord() {
        return squadRecord;
    }

    /**Provides the ID number with which the Team is registered in the Database.
     * @return An integer with the ID number.
     */

    @Override
    public int getID() {
        return this.id;
    }

    /**Provides an ID number that is not registered yet in the table "team" of the Database.
     * @return An integer with the ID number.
     */

    public static int generateID(){
        return DBUtils.generateID("team","team_id");
    }

    /**This method transforms the object Team into a String.
     * @return a String with the team's manager, its list of players and its squad record.
     */

    @Override
    public String toString() {
        return "Team{" +
                "manager=" + manager +
                ", playersList=" + playersList +
                ", squadRecord=" + squadRecord +
                '}';
    }

    /**Provides the list of players of the Team that play in a specific Position.
     * @param positionName Position in which players of the list play.
     * @return ArrayList<Player> with the list of players of the team that play in the Position provided as a parameter.
     */

    private ArrayList<Player> getPlayers(String positionName){
        ArrayList<Player> players = this.playersList;
        for(Player p : playersList) {
            if (p.getPosition().getName().equals(positionName)) {
                players.add(p);
            }
        }
        return players;
    }

    /**This method provides the total points obtained by the Team in a specific round of the league.
     * @param roundNumber Integer with the round number.
     * @return Integer with the total points obtained by the Team in the round provided as a parameter.
     */

    public int getPoints(int roundNumber){
        int totalPoints = 0;
        if(roundNumber>0 && roundNumber <= squadRecord.size()){
            for(Player p : squadRecord.get(roundNumber-1).getPlayersList()){
                totalPoints += p.getPoints(roundNumber);
            }
        }
        return totalPoints;
    }

    /**Provides the total points obtained by a Team in the League.
     * @return Integer with the total points obtained.
     */

    public int getTotalPoints(){
        int totalPoints = 0;
        for(int i = 0; i < squadRecord.size(); i++){
            totalPoints+= getPoints(i+1);
        }
        return totalPoints;
    }

    @Override
    public ArrayList<Player> getDefenders() {
        return getPlayers("Defense");
    }

    @Override
    public ArrayList<Player> getMidfielders() {
        return getPlayers("Midfielder");
    }

    @Override
    public ArrayList<Player> getForwards() {
        return getPlayers("Forward");
    }
}

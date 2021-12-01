package main.dataLogic.league;

import main.businessLogic.interfaces.IDBConnection;
import main.businessLogic.interfaces.IPositionClassification;
import main.dbManagement.DBUtils;
import main.dbManagement.DataExtraction;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;

import java.util.ArrayList;

/** Represents a virtual team that users of the game (managers) can manage.
 * @author Iker Villena Ona.
 */

public class Team implements IDBConnection, IPositionClassification {

    Manager manager;
    ArrayList<Player> playersList = generatePlayersList(DataExtraction.getAllPlayers());
    ArrayList<Squad> squadRecord = new ArrayList<>();

    public Team(Manager manager, ArrayList<Player> playersList, ArrayList<Squad> squadRecord) {
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
        return DataExtraction.getID("team","team_id","username",manager.getUsername());
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

    private ArrayList<Player> getPlayers(String positionName){
        ArrayList<Player> players = new ArrayList<>();
        for(Player p : playersList){
            if(p.getPosition().getName().equals(positionName)){
                players.add(p);
            }
        }
        return players;
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

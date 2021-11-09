package dataLogic.league;

import dataLogic.interfaces.IDBConnection;
import dbManagement.DBUtils;
import dbManagement.DataExtraction;
import dataLogic.people.Manager;
import dataLogic.people.Player;

import java.util.ArrayList;

/** Represents a virtual team that users of the game (managers) can manage.
 * @author Iker Villena Ona.
 */

public class Team implements IDBConnection {

    Manager manager;
    ArrayList<Player> playersList = generatePlayersList(DataExtraction.getAllPlayers());
    ArrayList<Squad> squadRecord = new ArrayList<>();

    public Team(Manager manager, ArrayList<Player> playersList, ArrayList<Squad> squadRecord) {
        this.manager = manager;
        this.playersList = playersList;
        this.squadRecord = squadRecord;
    }

    /**Team constructor that has an empty squadRecord.
     * @param manager
     * @param playersList
     */

    public Team(Manager manager, ArrayList<Player> playersList) {
        this.manager = manager;
        this.playersList = playersList;
    }

    /**Team constructor that has an empty squadRecord and that makes a playersList out of all the players of the league.
     * @param manager
     */

    public Team(Manager manager) {
        this.manager = manager;
    }

    //THE NUMBER OF PLAYERS PER POSITION MUST BE CHANGED
    public static ArrayList<Player> generatePlayersList(ArrayList<Player> allPlayers){
        ArrayList<Player> playersList = new ArrayList<>();
        boolean goalkeeper = false;
        int defenders = 0;
        int midfielders = 0;
        int forwards = 0;
        while(!goalkeeper || (defenders+midfielders+forwards) < 11){
            Player player = allPlayers.get((int) (Math.random()*allPlayers.size()));
            String positionName = player.getPosition().getName();
            if(positionName.equals("Goalkeeper") && !goalkeeper){
                playersList.add(player);
                goalkeeper = true;
            } else{
                if(positionName.equals("Defense") && defenders < 3){
                    playersList.add(player);
                    defenders++;
                } else{
                    if(positionName.equals("Midfielder") && midfielders < 3){
                        playersList.add(player);
                        midfielders++;
                    } else{
                        if(positionName.equals("Forward") && forwards < 5){
                            playersList.add(player);
                            forwards++;
                        }
                    }
                }
            }
            allPlayers.remove(player);
        }
        return playersList;
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

    @Override
    public int getID() {
        return DataExtraction.getID("team","team_id","username",manager.getUsername());
    }

    public static int generateID(){
        return DBUtils.generateID("team","team_id");
    }

    @Override
    public String toString() {
        return "Team{" +
                "manager=" + manager +
                ", playersList=" + playersList +
                ", squadRecord=" + squadRecord +
                '}';
    }
}

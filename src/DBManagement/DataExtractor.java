package DBManagement;

import DataLogic.League.Squad;
import DataLogic.League.Team;
import DataLogic.People.Attributes.Position;
import DataLogic.People.Manager;
import DataLogic.People.Player;

import java.sql.*;
import java.util.ArrayList;

/** This class has the aim of extracting data from de DataBase.
 * It provides the methods needed for that purpose.
 * @author Iker Villena Ona.
 */

public class DataExtractor {

    /**This method gets the value record of a specific player (whose id is provided as a parameter).
     * @param playerID
     * @return a list of floats (float[]) containing the value of the player in each of the rounds.
     */

    public static float[] getValueHistory(int playerID){
        String sql = "select round_num,value from value where player_ID = '" + playerID + "'";
        float[] valueHistory = new float[8];
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int round = rs.getInt("round_num");
                float value = rs.getFloat("value");
                valueHistory[round] = value;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valueHistory;
    }

    /**This method gets from the Database the list of positions.
     * @return an ArrayList which contains the positions.
     */

    public static ArrayList<Position> getPositions(){
        String sql = "select position_name,shortname,pts_goal,pts_assist,pts_nogoalsagainst," +
                "pts_goalsagainst from position";
        ArrayList<Position> positionsList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                positionsList.add(new Position(rs.getString("position_name"),rs.getString("shortname")
                        ,rs.getInt("pts_goal"),rs.getInt("pts_assist"),
                        rs.getInt("pts_nogoalsagainst"),rs.getInt("pts_goalsagainst")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return positionsList;
    }

    /**This method has the aim of providing a list with all the players registered in the Database.
     * @return an ArrayList with all the players.
     */

    public static ArrayList<Player> getAllPlayers(){
        String sql = "select name,surname,shirtname,number,position_name,player_id from player";
        ArrayList<Player> playersList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String shirtName = rs.getString("shirtname");
                int shirtNumber = rs.getInt("number");
                Position position = Position.getPosition(rs.getString("position_name"));
                float [] valueHistory = getValueHistory(rs.getInt("player_id"));
                playersList.add(new Player(name,surname,shirtName,shirtNumber,position,valueHistory));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playersList;
    }

    /**This method gets from the Database a Player whose id number is provided as a parameter.
     * @param player_id
     * @return a Player registered in the Database with the player_id provided.
     */

    private static Player getPlayer(int player_id){
        String sql = "select name,surname,shirtname,number,position_name,player_id from player where player_id = '"+player_id+"'";
        Player player = null;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String shirtName = rs.getString("shirtname");
                int shirtNumber = rs.getInt("number");
                Position position = Position.getPosition(rs.getString("position_name"));
                float [] valueHistory = getValueHistory(rs.getInt("player_id"));
                player = new Player(name,surname,shirtName,shirtNumber,position,valueHistory);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return player;
    }

    /**This method gets a list of players that form a specific team.
     * @param team_id
     * @return an ArrayList with the list of players that form the team whose id is the same as the one provided as a parameter.
     */

    private static ArrayList<Player> getTeamPlayers(int team_id){
        String sql = "select player_id from playfor where team_id="+team_id;
        ArrayList<Player> playersList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                playersList.add(getPlayer(rs.getInt("player_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playersList;
    }

    /**This method provides the list of all the managers registered in the Database.
     * @return an Arraylist with all the managers.
     */

    public static ArrayList<Manager> getManagers(){
        String sql = "select username,password,name,surname from manager";
        ArrayList<Manager> managersList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                managersList.add(new Manager(username,password,name,surname));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return managersList;
    }

    /**This method gets a specific manager from the Database
     * @param username
     * @return a Manager whose username is the same as the one provided as a parameter.
     */

    private static Manager getManager(String username){
        String sql = "select username,password,name,surname from manager where username='"+username+"'";
        Manager manager = null;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                manager = new Manager(username,password,name,surname);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return manager;
    }

    /**This method gets which is the next round of the league.
     * @return an integer with the number of the next round of the league.
     */

    public static int getNextRound(){
        String sql = "select MAX(round_num) as lastRound from round";
        int nextRound = 0;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                nextRound = rs.getInt("lastRound") + 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nextRound;
    }

    /**This method provides the squad referenced with the squad_id provided as a parameter.
     * @param squad_id
     * @return a Squad registered in the Database with the squad_id provided.
     */

    private static Squad getSquad(int squad_id){
        String sql = "select player_id from alignment where squad_id = "+squad_id;
        ArrayList<Player> playersList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                playersList.add(getPlayer(rs.getInt("player_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Squad.createSquad(playersList);
    }

    /**This method provides the squad record of a specific team.
     * @param team_id
     * @return an ArrayList with the list of Squads that form the squad record of the team.
     */

    private static ArrayList<Squad> getSquadRecord(int team_id){
        String sql = "select round_num, squad_id from squad where team_id ="+team_id+" order by round_num asc";
        ArrayList<Squad> squadRecord = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                squadRecord.add(getSquad(rs.getInt("squad_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return squadRecord;
    }

    /**This method gets the list of teams that form a specific league.
     * @param league_id
     * @return an ArrayList with the teams that form the league.
     */

    public static ArrayList<Team> getTeams(int league_id){
        String sql = "select username, team_id from team where league_id = "+league_id;
        ArrayList<Team> teamsList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int team_id = rs.getInt("team_id");
                teamsList.add(new Team(getManager("username"),getTeamPlayers(team_id),getSquadRecord(team_id)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teamsList;
    }

    /**This method provides the ID number of the league whose entry code is the one provided as a parameter.
     * @param entryCode
     * @return an integer with the ID number of the league.
     */

    public static int getLeagueID(String entryCode){
        int leagueID = 0;
        String sql = "select league_id from league where entrycode = '"+entryCode+"'";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leagueID = rs.getInt("league_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return leagueID;
    }

}

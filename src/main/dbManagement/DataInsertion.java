package main.dbManagement;

import main.businessLogic.Statistic;
import main.dataLogic.league.Bid;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/** This class contains methods needed to insert data into the Database.
 * @author Iker Villena Ona
 */

public class DataInsertion {

    /**Inserts all tha data of a league in their corresponding Database tables.
     * @param league the League that needs to be saved to the Database.
     */

    public static void newLeague(League league){

        insertLeague(league.getName(),league.getEntryCode());
        /*
        for(Team t : league.getTeamsList()){
            insertTeam(t, league);
        }
        */
        //Using Lambda expressions:
        league.getTeamsList()
                .stream()
                .forEach(team -> insertTeam(team, league));
    }

    /**Inserts a league in the Database.
     * @param name the league's name.
     * @param entryCode the league's entry code.
     */

    public static void insertLeague(String name, String entryCode) {
        String sql = "INSERT INTO league(league_id,season_id,name,entrycode) VALUES(?,?,?,?)";

        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, League.generateID());
            pstmt.setInt(2, DataExtraction.getCurrentSeason());
            pstmt.setString(3,name);
            pstmt.setString(4,entryCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Inserts a team in de Database.
     * @param team the team that needs to be saved to the Database.
     * @param league the league in which the team is participating.
     */

    public static void insertTeam(Team team, League league){
        String username = team.getManager().getUsername();
        int leagueID = league.getID();
        String sql = "INSERT INTO team(team_id,league_id,username,budget) VALUES(?,?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, team.getID());
            pstmt.setInt(2, leagueID);
            pstmt.setString(3, username);
            pstmt.setFloat(4, team.getBudget());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertPlayFor(team);
    }

    /**Inserts the relationship between players and a team in the "playfor" table of the Database.
     * @param team the Team in which players play.
     */

    private static void insertPlayFor(Team team){
        for(Player player : team.getPlayersList()){
            String sql = "INSERT INTO playfor(player_id, team_id) VALUES(?,?)";
            try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, player.getID());
                pstmt.setInt(2,team.getID());
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**Saves the statistics of a player in the Database.
     * @param statistic A Statistic of a player in a specific match.
     * @param player A Player whose statistics must be saved to the DataBase.
     */

    public static void insertStatistic(int roundNum, Player player, Statistic statistic) {
        String sql = "INSERT INTO statistic(round_num, player_id, played, goals, assists, goalsagainst, yellowcards, redcard) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,roundNum);
            pstmt.setInt(2, player.getID());
            pstmt.setBoolean(3,statistic.isPlayed());
            pstmt.setInt(4,statistic.getNumGoals());
            pstmt.setInt(5,statistic.getNumAssists());
            pstmt.setInt(6,statistic.getReceivedGoals());
            pstmt.setInt(7,statistic.getYellowCards());
            pstmt.setBoolean(8,statistic.isRedCard());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Saves the alignments of players in a Squad to the Dataabase.
     * @param team Team in which players are aligned.
     * @param playersList ArrayList with the list of players aligned.
     */
    private static void insertAlignment(Team team, ArrayList<Player> playersList){
        for (Player p : playersList){
            String sql = "INSERT INTO alignment(player_id, team_id, round_num) VALUES(?,?,?)";
            try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1,p.getID());
                pstmt.setInt(2,team.getID());
                pstmt.setInt(3,DataExtraction.getNextRound());
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**Saves a team's Squad to the Database (tables "squad" and "alignment").
     * @param team Team in which players are aligned.
     * @param squad Squad that is saved to the Database.
     */

    public static void insertSquad(Team team, Squad squad){
        String sql = "INSERT INTO squad(team_id,round_num,formation_id) VALUES(?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,team.getID());
            pstmt.setInt(2,DataExtraction.getNextRound());
            pstmt.setInt(3,squad.getFormation().getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertAlignment(team, squad.getPlayersList());
    }

    /**Saves the new value of a player after a specific round of the league.
     * @param player Player whose value must be saved.
     * @param roundNum Integer with the round number.
     * @param value Float with the new value of the player.
     */

    public static void insertValue(Player player, int roundNum, float value){
        String sql = "INSERT INTO value(player_id,round_num,value) VALUES(?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,player.getID());
            pstmt.setInt(2,roundNum);
            pstmt.setFloat(3,value);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Inserts a bid into the DataBase.
     * @param bid Bid that needs to be saved
     */

    public static void insertBid(Bid bid){
        String sql = "INSERT INTO bid(from_team,to_team,player_id,fee) VALUES(?,?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,bid.getInterestedTeam().getID());
            if(bid.getCurrentTeam() != null){
                pstmt.setInt(2,bid.getCurrentTeam().getID());
            }
            pstmt.setInt(3,bid.getPlayer().getID());
            pstmt.setFloat(4,bid.getFee());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Inserts a Manager into the DataBase.
     * @param manager Manager whose information needs to be saved in the DataBase.
     */

    public static void insertManager(Manager manager){
        String sql = "INSERT INTO manager(username,password,name,surname) VALUES(?,?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, manager.getUsername());
            pstmt.setString(2, manager.getPassword());
            pstmt.setString(3, manager.getName());
            pstmt.setString(4, manager.getSurname());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Inserts a player into a Team.
     * @param player Player that needs to be saved.
     * @param team Team in which the Player plays now.
     */

    public static void insertPlayFor(Player player, Team team){
        String sql = "INSERT INTO playfor(player_id, team_id) VALUES(?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, player.getID());
            pstmt.setInt(2,team.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

package main.dbManagement;

import main.businessLogic.Bid;
import main.businessLogic.Statistic;
import main.businessLogic.TacticalFormation;
import main.dataLogic.league.Club;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Administrator;
import main.dataLogic.people.attributes.Position;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;

import java.sql.*;
import java.util.ArrayList;

/** This class contains methods needed to extract data from the Database.
 * @author Iker Villena Ona
 */

public class DataExtraction {

    /**This method gets the value record of a specific player (whose id is provided as a parameter).
     * @param playerID ID number of a player.
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

    /**Provides a list with all the tactical formations registered in the Database.
     * @return An ArrayList<TacticalFormation> with all the formations.
     */

    public static ArrayList<TacticalFormation> getAllFormations(){
        ArrayList<TacticalFormation> formationsList = new ArrayList<>();
        String sql = "select numDefenders, numMidfielders, numForwards from tacticalformation";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int numDefenders = rs.getInt("numDefenders");
                int numMidfielders = rs.getInt("numMidfielders");
                int numForwards = rs.getInt("numForwards");
                formationsList.add(new TacticalFormation(numDefenders, numMidfielders, numForwards));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return formationsList;
    }

    /**Provides a tactical formation referenced by its formationID.
     * @param formationID Integer with the formation's ID number.
     * @return The TacticalFormation registered in the Database with the ID number provided as a parameter.
     */

    public static TacticalFormation getFormation(int formationID){
        TacticalFormation formation = null;
        String sql = "select numDefenders, numMidfielders, numForwards from tacticalformation where formation_id = " + formationID;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int numDefenders = rs.getInt("numDefenders");
                int numMidfielders = rs.getInt("numMidfielders");
                int numForwards = rs.getInt("numForwards");
                formation = new TacticalFormation(numDefenders, numMidfielders, numForwards);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return formation;
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
        String sql = "select player_id from player";
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

    /**This method gets from the Database a Player whose id number is provided as a parameter.
     * @param player_id the ID number of a player.
     * @return a Player registered in the Database with the player_id provided.
     */

    public static Player getPlayer(int player_id){
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
                ArrayList<Statistic> statsRecord = getStatistics(player_id);
                player = new Player(name,surname,shirtName,shirtNumber,position,valueHistory,statsRecord);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return player;
    }

    /**This method gets a list of players that form a specific team.
     * @param team_id the ID number of a team.
     * @return an ArrayList with the list of players that form the team whose id is the same as the one provided as a parameter.
     */

    public static ArrayList<Player> getTeamPlayers(int team_id){
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

    public static ArrayList<Manager> getAllManagers(){
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
     * @param username a Manager's username.
     * @return a Manager whose username is the same as the one provided as a parameter.
     */

    public static Manager getManager(String username){
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
     * For that: it checks that previous rounds have the statistics of all the players saved.
     * @return an integer with the number of the next round of the league.
     */

    public static int getNextRound(){
        int roundNum = 1;
        while(statsSaved(roundNum)){
            roundNum++;
        }
        return roundNum;
    }

    /**Checks if all the players have their statistics saved for a specific round number.
     * @param roundNum Integer with the round number that needs to be checked.
     * @return A boolean with the value "true" if the statistics of all the players are saved and "false" if they are not.
     */

    public static boolean statsSaved(int roundNum){
        String sql = "select count(stats_id) as numStats from statistic where round_num = "+roundNum;
        int numStats = 0;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                numStats = rs.getInt("numStats");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(numStats == getAllPlayers().size()){
            return true;
        } else{
            return false;
        }
    }

    /**Checks whether a player has his statistics for a specific round number saved or not.
     * @param roundNum Integer with the round number.
     * @param playerID Integer with the player's ID number.
     * @return A boolean with the value "true" if the statistics for that round are saved and "false" if they are not.
     */

    public static boolean playerStatsSaved(int roundNum,int playerID){
        String sql = "select count(stats_id) as numStats from statistic where round_num = "+roundNum+" and player_id = "+playerID;
        boolean statsSaved = false;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(rs.getInt("numStats")>0){
                    statsSaved = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return statsSaved;
    }

    /**This method gets which is the current season.
     * @return an integer with the number of season.
     */

    public static int getCurrentSeason(){
        String sql = "select MAX(season_num) as currentSeason from season";
        int currentSeason = 0;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                currentSeason = rs.getInt("currentSeason");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return currentSeason;
    }


    /**This method provides the list of players that are aligned in a squad referenced with the squad_id provided as a parameter.
     * @param teamID Integer with a team's ID number.
     * @param roundNum Integer with the round number.
     * @return An ArrayList<Player> with the list of player aligned in the Squad registered in the Database with the squad_id provided.
     */

    public static ArrayList<Player> getSquadPlayers(int teamID, int roundNum){
        String sql = "select player_id from alignment where team_id = "+teamID+" and round_num = "+roundNum;
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

    /**This method provides the squad record of a specific team.
     * @param teamID the ID number of a team
     * @return an ArrayList with the list of Squads that form the squad record of the team.
     */

    public static ArrayList<Squad> getSquadRecord(int teamID){
        String sql = "select round_num, formation_id from squad where team_id ="+teamID+" order by round_num asc";
        ArrayList<Squad> squadRecord = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                squadRecord.add(new Squad(rs.getInt("round_num"),getFormation(rs.getInt("formation_id")),getSquadPlayers(teamID, rs.getInt("round_num"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return squadRecord;
    }

    /**This method gets the list of teams that form a specific league.
     * @param league_id the ID number of a league.
     * @return an ArrayList with the teams that form the league.
     */

    public static ArrayList<Team> getTeams(int league_id){
        String sql = "select team_id, budget, username from team where league_id = "+league_id;
        ArrayList<Team> teamsList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int team_id = rs.getInt("team_id");
                teamsList.add(new Team(team_id,rs.getFloat("budget"),
                        getManager(rs.getString("username")),getTeamPlayers(team_id),getSquadRecord(team_id)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teamsList;
    }

    /**This method provides the ID number of the league whose entry code is the one provided as a parameter.
     * @param entryCode a league's entry code.
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

    /**Provides an ID number that is registered in the Database.
     * @param table the table's name.
     * @param idColumn the table's column where the ID numbers are registered.
     * @param valueColumn the table's column that is used to find a specific value.
     * @param value the value wanted.
     * @return the ID number that matches the parameters.
     */

    public static int getID(String table, String idColumn, String valueColumn, String value){
        int id = 0;
        String sql = "select "+idColumn+" from "+table+" where "+valueColumn+" = '"+value+"'";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                id = rs.getInt(idColumn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**Provides the ID number of a TacticalFormation that is registered in the DataBase.
     * @param numDefenders Integer with the number of defenders of the formation.
     * @param numMidfielders Integer with the number of midfielders of the formation.
     * @param numForwards Integer with the number of forwards of the formation.
     * @return Integer with the ID number of the formation.
     */

    public static int getFormationID(int numDefenders, int numMidfielders, int numForwards){
        int formationID = 0;
        String sql = "select formation_id from tacticalformation where numdefenders = "+numDefenders+" and nummidfielders = "+
                numMidfielders+" and numforwards = "+numForwards;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                formationID = rs.getInt("formation_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return formationID;
    }

    /**Provides all the leagues that are registered in the Database.
     * @return An ArrayList with all the leagues that are registered in the Database.
     */

    public static ArrayList<League> getAllLeagues(){
        ArrayList<League> leaguesList = new ArrayList<>();
        String sql = "select distinct name, entrycode, league_id from league";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                leaguesList.add(new League(rs.getString("name"),rs.getString("entrycode"),getTeams(rs.getInt("league_id"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return leaguesList;
    }

    /**Provides all the leagues in which a manager is participating.
     * @param username the manager's username.
     * @return An ArrayList with all the manager's leagues.
     */

    public static ArrayList<League> getManagerLeagues(String username){
        ArrayList<League> allLeagues = getAllLeagues();
        ArrayList<League> managerLeagues = new ArrayList<>();
        for(League l : allLeagues){
            for(Team t : l.getTeamsList()){
                if(t.getManager().getUsername().equals(username)){
                    managerLeagues.add(l);
                }
            }
        }
        return managerLeagues;
    }

    /**provides a League whose entry code is the same as the one provided as a parameter.
     * @param entryCode a league's entry code.
     * @return a League which has the entry code provided.
     */
    public static League getLeague(String entryCode){
        League league = null;
        for(League l : getAllLeagues()){
            if(l.getEntryCode().equals(entryCode)){
                league = l;
            }
        }
        return league;
    }

    /**Provides the list of players that play for a specific club.
     * @param clubName String that contains the club's name.
     * @return an ArrayList with the list of players that play for the club.
     */

    public static ArrayList<Player> getClubPlayers(String clubName){
        String sql = "select player_id from player where club_name = '"+clubName+"'";
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

    /**Provides a list with all the clubs registered in the Database.
     * @return an ArrayList with all the clubs.
     */

    public static ArrayList<Club> getAllClubs(){
        ArrayList<Club> clubsList = new ArrayList<>();
        String sql = "select distinct club_name from club";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String clubName = rs.getString("club_name");
                //System.out.println(clubName);
                clubsList.add(new Club(clubName,getClubPlayers(clubName)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clubsList;
    }

    public static int numUnsavedMatches(int roundNum){
        String sql = "select homeclub,awayclub from match where round_num = "+roundNum;
        int unsavedMatches = 0;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(!playerStatsSaved(roundNum,getClubPlayers(rs.getString("homeclub")).get(0).getID())) {
                    unsavedMatches++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return unsavedMatches;
    }

    /**Provides an array with the matches that correspond to a specific round of the league.
     * @param roundNum Integer with the round number.
     * @return a String[4][2] with the names of the clubs that play in each of the four matches (two clubs for each match).
     */

    public static String[][] getMatches(int roundNum){
        String[][] matchesList = new String[numUnsavedMatches(roundNum)][2];
        String sql = "select homeclub,awayclub from match where round_num = "+roundNum;
        int i = 0;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(!playerStatsSaved(roundNum,getClubPlayers(rs.getString("homeclub")).get(0).getID())) {
                    matchesList[i][0] = rs.getString("homeclub");
                    matchesList[i][1] = rs.getString("awayclub");
                    i++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return matchesList;
    }

    public static ArrayList<Statistic> getStatistics(int playerID){
        ArrayList<Statistic> statisticsList = new ArrayList<>();
        String sql = "select played,goals,assists,goalsagainst,yellowcards,redcard from statistic where player_id = "+playerID;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                boolean played = rs.getBoolean("played");
                int numGoals = rs.getInt("goals");
                int numAssists = rs.getInt("assists");
                int receivedGoals = rs.getInt("goalsagainst");
                int yellowCards = rs.getInt("yellowcards");
                boolean redCard = rs.getBoolean("redcard");
                statisticsList.add(new Statistic(played,numGoals,numAssists,receivedGoals,yellowCards,redCard));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return statisticsList;
    }

    public static ArrayList<Administrator> getAllAdministrators(){
        ArrayList<Administrator> listAdmins = new ArrayList<>();
        String sql = "select username, password, fullaccess from administrator";
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listAdmins.add(new Administrator(rs.getString("username"),rs.getString("password"),
                        rs.getBoolean("fullaccess")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listAdmins;
    }

    public static ArrayList<Player> searchPlayer(String clue){
        ArrayList<Player> playersList = new ArrayList<>();
        String sql = "select player_id from player where name like '%"+clue+"%' OR surname like '%"+clue+"%' OR " +
                "shirtname like '%"+clue+"%'";
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

    public static Team getTeam(int teamID){
        Team team = null;
        String sql = "select team_id, budget, username from team where team_id = "+teamID;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int team_id = rs.getInt("team_id");
                team = new Team(team_id,rs.getFloat("budget"),
                        getManager(rs.getString("username")),getTeamPlayers(team_id),getSquadRecord(team_id));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return team;
    }

    public static ArrayList<Bid> getTeamBids(Team team){
        ArrayList<Bid> bidsList = new ArrayList<>();
        String sql = "SELECT from_team, IFNULL(to_team,-1) AS to_team, player_id, fee FROM bid WHERE from_team = "+team.getID()+" OR to_team = "
                + team.getID();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Team toTeam = null;
                if(rs.getInt("to_team")>=0){
                    toTeam = getTeam(rs.getInt("to_team"));
                }
                bidsList.add(new Bid(getTeam(rs.getInt("from_team")),toTeam,
                        getPlayer(rs.getInt("player_id")),rs.getFloat("fee")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bidsList;
    }

    public static ArrayList<Bid> getLeagueBids(League league){
        ArrayList<Bid> bidsList = new ArrayList<>();
        String sql = "SELECT league_id, from_team, player_id, fee FROM bid INNER JOIN team " +
                "ON bid.from_team = team.team_id WHERE to_team IS NULL AND league_id = "+league.getID();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bidsList.add(new Bid(getTeam(rs.getInt("from_team")),null,
                        getPlayer(rs.getInt("player_id")),rs.getFloat("fee")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bidsList;
    }

}

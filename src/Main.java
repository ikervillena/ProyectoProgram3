import DBManagement.DBManager;

public class Main {

    public static void main(String[] args){

        DBManager.createNewDatabase("ProjectDB.db");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS SEASON(SEASON_NUM INTEGER NOT NULL PRIMARY KEY);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS ROUND(ROUND_NUM INTEGER NOT NULL PRIMARY KEY);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS MANAGER(USERNAME VARCHAR(25) NOT NULL PRIMARY KEY,"
                + "PASSWORD VARCHAR(25) NOT NULL,"
                + "NAME VARCHAR(25) NOT NULL,"
                + "SURNAME VARCHAR(25) NOT NULL);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS LEAGUE(LEAGUE_ID INTEGER NOT NULL PRIMARY KEY,"
                + "SEASON_ID INTEGER NOT NULL REFERENCES SEASON(SEASON_NUM) ON DELETE CASCADE,"
                + "NAME VARCHAR(25) NOT NULL,"
                + "ENTRYCODE VARCHAR(25) NOT NULL UNIQUE);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS TEAM(TEAM_ID INTEGER NOT NULL PRIMARY KEY,"
                + "LEAGUE_ID INTEGER NOT NULL REFERENCES LEAGUE(LEAGUE_ID) ON DELETE CASCADE,"
                + "MANAGER_ID INTEGER NOT NULL REFERENCES MANAGER(MANAGER_ID) ON DELETE CASCADE);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS MATCH(ROUND_ID INTEGER NOT NULL PRIMARY KEY,"
                + "HOMETEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "AWAYTEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "HOMEGOALS INTEGER NOT NULL,"
                + "AWAYGOALS INTEGER NOT NULL);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS CLUB(CLUB_NAME VARCHAR(25) NOT NULL PRIMARY KEY);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS POSITION(POSITION_NAME VARCHAR(25) NOT NULL PRIMARY KEY,"
                + "SHORTNAME CHAR(3) NOT NULL UNIQUE,"
                + "PTS_GOAL INTEGER NOT NULL,"
                + "PTS_ASSIST INTEGER NOT NULL,"
                + "PTS_NOGOALSAGAINST INTEGER NOT NULL,"
                + "PTS_GOALSAGAINST INTEGER NOT NULL);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS SQUAD(SQUAD_ID INTEGER NOT NULL PRIMARY KEY,"
                + "TEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "ROUND_NUM INTEGER NOT NULL REFERENCES ROUND(TOUND_NUM) ON DELETE CASCADE);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS PLAYER(PLAYER_ID INTEGER NOT NULL PRIMARY KEY,"
                + "CLUB_NAME VARCHAR(25) NOT NULL REFERENCES CLUB(CLUB_NAME) ON DELETE CASCADE,"
                + "POSITION_NAME VARCHAR(25) NOT NULL REFERENCES POSITION(POSITION_NAME) ON DELETE CASCADE,"
                + "NAME VARCHAR(25) NOT NULL,"
                + "SURNAME VARCHAR(25) NOT NULL,"
                + "NUMBER INTEGER NOT NULL,"
                + "SHIRTNAME VARCHAR(15) NOT NULL);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS STATISTIC(STATS_ID INTEGER NOT NULL PRIMARY KEY,"
                + "ROUND_NUM INTEGER NOT NULL REFERENCES ROUND(TOUND_NUM) ON DELETE CASCADE,"
                + "PLAYER_ID INTEGER NOT NULL REFERENCES PLAYER(PLAYER_ID) ON DELETE CASCADE,"
                + "PLAYED BOOLEAN NOT NULL,"
                + "GOALS INTEGER NOT NULL,"
                + "ASSITS INTEGER NOT NULL,"
                + "GOALSAGAINST INTEGER NOT NULL,"
                + "YELLOWCARDS INTEGER NOT NULL"
                + " CHECK (YELLOWCARDS <=1),"
                + "REDCARD BOOLEAN NOT NULL);");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS PLAYFOR(PLAYER_ID INTEGER NOT NULL REFERENCES PLAYER(PLAYER_ID) ON DELETE CASCADE,"
                + "TEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "PRIMARY KEY (PLAYER_ID, TEAM_ID));");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS ALIGNMENT(PLAYER_ID INTEGER NOT NULL REFERENCES PLAYER(PLAYER_ID) ON DELETE CASCADE,"
                + "SQUAD_ID INTEGER NOT NULL REFERENCES SQUAD(SQUAD_ID) ON DELETE CASCADE,"
                + "PRIMARY KEY (PLAYER_ID, SQUAD_ID));");
        DBManager.createNewTable("CREATE TABLE IF NOT EXISTS TRANSFER(TRANSFER_ID INTEGER NOT NULL PRIMARY KEY,"
                + "OLTEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "NEWTEAM_ID INTEGER NOT NULL REFERENCES TEAM(TEAM_ID) ON DELETE CASCADE,"
                + "PLAYER_ID INTEGER NOT NULL REFERENCES PLAYER_ID(PLAYER_ID) ON DELETE CASCADE,"
                + "FEE INTEGER NOT NULL,"
                + "DATE DATE NOT NULL);");

    }

}

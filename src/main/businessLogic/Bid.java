package main.businessLogic;

import main.dataLogic.league.Team;
import main.dataLogic.people.Player;

public class Bid {

    Team interestedTeam;
    Team currentTeam;
    Player player;
    float fee;

    public Bid(Team interestedTeam, Team currentTeam, Player player, float fee) {
        this.interestedTeam = interestedTeam;
        this.currentTeam = currentTeam;
        this.player = player;
        this.fee = fee;
    }

    public Team getInterestedTeam() {
        return interestedTeam;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public Player getPlayer() {
        return player;
    }

    public float getFee() {
        return fee;
    }
}

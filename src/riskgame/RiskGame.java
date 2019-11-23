package riskgame;

import java.util.ArrayList;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;

public class RiskGame {

    public static void main(String[] args) {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new Passive();
        Player player2 = new Passive();

        player1.setOpponent(player2);
        player2.setOpponent(player1);
        utils.divideTerritoriesRandom(player1, player2, usaTerritories);

        State state = new State(usaTerritories, player1);
        player1.setBonusTroops(50);
        player2.setBonusTroops(50);

        player1.play(state);
    }

}

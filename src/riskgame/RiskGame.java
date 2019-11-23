package riskgame;

import java.util.ArrayList;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;

public class RiskGame {

    public static void main(String[] args) {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new Passive(0);
        Player player2 = new Passive(1);

        utils.divideTerritoriesRandom(player1, player2, usaTerritories);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        State state = new State(usaTerritories, players);
        player1.divideTroopsRandom(state);
        player2.divideTroopsRandom(state);
        player1.setBonusTroops(50);
        player2.setBonusTroops(50);

        player1.play(state);
    }

}

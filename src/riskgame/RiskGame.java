package riskgame;

import java.util.ArrayList;
import riskgame.Agents.A_Star;
import riskgame.Agents.Aggressive;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;

public class RiskGame {

    public static void main(String[] args) {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new Aggressive(0);
        Player player2 = new Aggressive(1);

        utils.divideTerritoriesRandom(player1, player2, usaTerritories);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        State state = new State(usaTerritories, players);
        player1.divideTroopsRandom(state, 50);
        player2.divideTroopsRandom(state, 50);

        Utils.printState(state);

        new RiskGame().startGame(state);
    }

    private void startGame(State state) {
        while (true) {
            for (int i = 0; i < state.getPlayers().size(); i++) {

                if (isGameEnded(state)) {
                    return;
                }
                state.setPlayerTurn(i);
                state.getPlayers().get(i).addBonusTroops();
                state = state.getPlayers().get(i).play(state);
            }
        }
    }

    public boolean isGameEnded(State state) {
        for (Player player : state.getPlayers()) {

            if (player.getTerritories().size() == 0) {
                System.out.println("Player:" + player.getTurn() + " Lost");
                return true;
            }
        }
        return false;
    }

}

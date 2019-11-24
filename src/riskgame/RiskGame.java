package riskgame;

import java.util.ArrayList;

import riskgame.Agents.Aggressive;
import riskgame.Agents.MiniMax;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;

public class RiskGame {

    public static void main(String[] args) {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new MiniMax(0);
        Player player2 = new Passive(1);

        utils.divideTerritoriesRandom(player1, player2, usaTerritories);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        State state = new State(usaTerritories, players, 0);
        player1.divideTroopsRandom(state, 50);
        player2.divideTroopsRandom(state, 50);

        Utils.printState(state);

        new RiskGame().startGame(state);
    }

    private void startGame(State state) {
        while (true) {
            for (int i = 0; i < state.getPlayers().size(); i++) {
                Utils.printState(state);

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

//    public static void printState(State temp) {
//        ArrayList<Integer> s = new ArrayList<>(temp.getPlayers().get(0).getTerritories());
//        System.out.println("MinMax ownes: ");
//        for (int i = 0; i < s.size(); i++) {
//            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
//        }
//        System.out.println("");
//
//        s = new ArrayList<>(temp.getPlayers().get(1).getTerritories());
//        System.out.println("Pssive ownnes: ");
//        for (int i = 0; i < s.size(); i++) {
//            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
//        }
//        System.out.println("");
//    }
}

package riskgame;

import java.util.ArrayList;
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
        player1.setBonusTroops(50);
        player2.setBonusTroops(50);
        System.out.println("--" + player1.getTerritories().size());
        State state = new State(usaTerritories, players,0);

        player1.divideTroopsRandom(state);

       player2.divideTroopsRandom(state);

        play(state);
    }

    private static void play(State state) {
        int i=0;
        while (!RiskGame.isGameEnded(state)) {
            System.out.println("sassa");
            state.getPlayers().get(0).setBonusTroops(state.getPlayers().get(0).getTerritories().size() / 3);

            State s = state.getPlayers().get(0).play(state);
            state.getPlayers().get(1).setBonusTroops(state.getPlayers().get(1).getTerritories().size() / 3);
            State b = s.getPlayers().get(1).play(s);
            state=(State) b.clone();
            i++;
        }
        System.out.println("Winning in "+i+" turns");
    }

    public static boolean isGameEnded(State state) {
        for (Player player : state.getPlayers()) {
            if (player.getTerritories().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void printState(State temp) {
         ArrayList<Integer> s = new ArrayList<>(temp.getPlayers().get(0).getTerritories());
        System.out.println("MinMax ownes: ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
        }
        System.out.println("");

        s = new ArrayList<>(temp.getPlayers().get(1).getTerritories());
        System.out.println("Pssive ownnes: ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
        }
        System.out.println("");
    }
}

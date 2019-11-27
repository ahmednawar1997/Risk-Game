package riskgame;

import java.util.ArrayList;
import javafx.scene.Scene;
import riskgame.Agents.A_Star;

import riskgame.Agents.Aggressive;
import riskgame.Agents.MiniMax;
import riskgame.Agents.Pacifist;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;

public class RiskGame {

   
    public boolean initGame(Scene scene){
         Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new MiniMax(0, 7, 2.5, true);
        Player player2 = new Aggressive(1);

        //Player player3 = new Aggressive(2);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        // players.add(player3);
        utils.divideTerritoriesRandom(players, usaTerritories);

        State state = new State(usaTerritories, players, 0,scene);
        utils.divideTroops(state);

        Utils.printState(state);
        new Controller().Recolor(scene, state);
        new RiskGame().startGame(state);
        return true;
    }

    private void startGame(State state) {
        int turns = 0;
        while (true) {
            turns++;
            for (int i = 0; i < state.getPlayers().size(); i++) {
                Utils.printState(state);

                if (isGameEnded(state)) {
                    System.out.println("Turns = " + turns);
                    return;
                }
                state.setPlayerTurn(i);
                state.getPlayers().get(i).addBonusTroops();
                state = state.getPlayers().get(i).play(state);
                System.out.println("*************************************************************");
            }
        }

    }

    public boolean isGameEnded(State state) {
        int numOfSurviving = 0;
        for (Player player : state.getPlayers()) {

            if (!player.getTerritories().isEmpty()) {

                numOfSurviving++;

            }
        }
        if (numOfSurviving == 1) {
            return true;
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

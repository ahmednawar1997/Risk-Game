package riskgame;

import java.util.ArrayList;
import riskgame.Agents.Player;

public class Heuristic {

    public static int evaluateUtility(State state) {

        return 50 - state.getPlayers().get(1).getTerritories().size();
    }

    public static double evaluateTerritory(Integer get, State newState) {

        double optroops = 0.0;
        Territory t = newState.getTerritories().get(get - 1);
        double mytroops = t.getNumberOfTroops();
        ArrayList<Territory> a = newState.getPlayers().get(newState.getPlayerTurn()).getAttackableNeighbours(t, newState);
        for (int i = 0; i < a.size(); i++) {
            optroops += a.get(i).getNumberOfTroops();
        }

        return optroops / mytroops;
    }

    public static double calculateHeuristic(Player player, State newState) {
        double Hn = getTotalBSR(player, newState);
        Hn += newState.getTerritories().size() - newState.getPlayers().get(player.getTurn()).getTerritories().size();
        return Hn;
    }

    public static double getTotalBSR(Player player, State newState) {

        double totalBSR = 0.0;
        for (int number : player.getTerritories()) {
            totalBSR += BSR(newState.getTerritories().get(number - 1), newState);
        }
        return totalBSR;
    }

    public static double BSR(Territory territory, State newState) {

        double optroops = 0.0;
        double mytroops = territory.getNumberOfTroops();
        ArrayList<Territory> a = newState.getPlayers().get(newState.getPlayerTurn()).getAttackableNeighbours(territory, newState);
        for (int i = 0; i < a.size(); i++) {
            optroops += a.get(i).getNumberOfTroops();
        }

        return (optroops / mytroops) * 1.0;
    }

    public static double evaluateAttack(int number, int get, State temp) {
        double att = (temp.getTerritories().get(number - 1).getNumberOfTroops() - 1) / 3;
        double def = (temp.getTerritories().get(get - 1).getNumberOfTroops()) / 2;

        return att / def;
    }

    public static int cost1(State state) {
        ArrayList<Territory> territories = state.getTerritories();

        return 0;
    }

//    public static int evaluateUtility(State state, Player player) {
//        if (player.getTerritories().size() == 0) {
//            return 100;
//        }
//
//        if (player.getOpponent().getTerritories().size() == 0) {
//            return -100;
//        }
//        return 0;
//    }
}

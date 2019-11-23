package riskgame;

import java.util.ArrayList;
import riskgame.Agents.Player;

public class Heuristic {

    private Heuristic() {
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

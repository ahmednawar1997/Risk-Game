package riskgame;

import java.util.ArrayList;

public class Heuristic {

    private Heuristic() {
    }

    public static int cost1(State state) {
        ArrayList<Territory> territories = state.getTerritories();

        return 0;
    }

    public static int evaluateUtility(State state) {
        if (state.getPlayer().getTerritories().size() == 0) {
            return 100;
        }

        if (state.getPlayer().getOpponent().getTerritories().size() == 0) {
            return -100;
        }
        return 0;
    }

}

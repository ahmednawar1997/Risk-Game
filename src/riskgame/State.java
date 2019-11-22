package riskgame;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Agents.Player;

public class State implements Cloneable {

    private ArrayList<Territory> territories;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    public int evaluateUtility() {
        if (getPlayer().getTerritories().size() == 0) {
            return 100;
        }

        if (getPlayer().getOpponent().getTerritories().size() == 0) {
            return -100;
        }
        return 0;
    }

    public Territory getTerritoryWithLowestTroops() {
        int min = 10000;
        Territory lowestTerritory = null;
        for (Territory territory : this.territories) {
            if (min == 1) {
                break;
            }
            if (territory.getNumberOfTroops() < min) {
                lowestTerritory = territory;
                min = territory.getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }

    public Object clone() {

        try {
            State cloned = (State) super.clone();
            cloned.setPlayer((Player) cloned.getPlayer().clone());
            cloned.setTerritories((ArrayList<Territory>) cloned.getTerritories().clone());

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}

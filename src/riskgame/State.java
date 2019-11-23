package riskgame;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Agents.Player;

public class State implements Cloneable {

    private ArrayList<Territory> territories = new ArrayList<>();
    private Player player;

    public State(ArrayList<Territory> territories, Player player) {
        this.territories = territories;
        this.player = player;
    }

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

    public Object clone() {
        State cloned = null;
        try {
            cloned = (State) super.clone();
            ArrayList<Territory> ts = new ArrayList<>();
            ts = cloneArrayList(cloned.getTerritories());
            cloned.setTerritories(ts);
            cloned.setPlayer((Player) cloned.getPlayer().clone(cloned));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }

    public boolean isGameEnded() {
        if (this.getPlayer().getTerritories().size() == 0 || this.getPlayer().getOpponent().getTerritories().size() == 0) {
            return true;
        }
        return false;
    }

    private ArrayList<Territory> cloneArrayList(ArrayList<Territory> territories) {
        ArrayList<Territory> ts = new ArrayList<>();
        for (Territory t : territories) {
            ts.add((Territory) t.clone());
        }

        return ts;
    }

}

package riskgame;

import java.util.ArrayList;
import riskgame.Agents.Player;

public class State {

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

}

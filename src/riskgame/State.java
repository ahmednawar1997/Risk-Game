package riskgame;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Agents.Player;

public class State implements Cloneable {

    private ArrayList<Territory> territories = new ArrayList<>();
    private ArrayList<Player> players;
    private int playerTurn;

    public State(ArrayList<Territory> territories, ArrayList<Player> players) {
        this.territories = territories;
        this.players = players;
        this.playerTurn = 0;

    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

//    public int evaluateUtility() {
//        if (getPlayer1().getTerritories().size() == 0) {
//            return 100;
//        }
//
//        if (getPlayer2().getTerritories().size() == 0) {
//            return -100;
//        }
//        return 0;
//    }
    public Object clone() {
        State cloned = null;
        try {
            cloned = (State) super.clone();
            ArrayList<Player> players = new ArrayList<>();
            players = cloneArrayListPlayers(cloned.getPlayers(), cloned);
            
            ArrayList<Territory> ts = new ArrayList<>();
            ts = cloneArrayListTerritories(cloned.getTerritories(), cloned);
            cloned.setTerritories(ts);
            cloned.setPlayers(players);

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }





    private ArrayList<Territory> cloneArrayListTerritories(ArrayList<Territory> territories, State state) {
        ArrayList<Territory> ts = new ArrayList<>();
        for (Territory t : territories) {
            ts.add((Territory) t.clone(state));
        }

        return ts;
    }

    private ArrayList<Player> cloneArrayListPlayers(ArrayList<Player> players, State state) {
        ArrayList<Player> ps = new ArrayList<>();
        for (Player p : players) {
            ps.add((Player) p.clone(state));
        }

        return ps;
    }
}

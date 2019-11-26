package riskgame;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Agents.Player;

public class State implements Cloneable, Comparable<State> {

    private ArrayList<Territory> territories = new ArrayList<>();
    private ArrayList<Player> players;
    private int playerTurn;
    private int depth;

    private double cost;
    private State previousState;
    private StateActions actions;

  
    public State(ArrayList<Territory> territories, ArrayList<Player> players, int depth) {

        this.territories = territories;
        this.players = players;
        this.playerTurn = 0;

      
        this.depth = depth;
        this.actions = new StateActions();

    }



    public StateActions getActions() {
        return actions;
    }

    public void setActions(StateActions actions) {
        this.actions = actions;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
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

    @Override
    public int compareTo(State o) {
        return Double.compare(this.cost, o.getCost());
    }
}

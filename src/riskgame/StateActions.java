package riskgame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StateActions implements Cloneable {

    private int attackingTerritory;
    private int defendingTerritory;
    private boolean endTurn = false;

    public StateActions(int attackingTerritory, int defendingTerritory) {
        this.attackingTerritory = attackingTerritory;
        this.defendingTerritory = defendingTerritory;
    }

    public StateActions() {
    }

    public int getAttackingTerritory() {
        return attackingTerritory;
    }

    public void setAttackingTerritory(int attackingTerritory) {
        this.attackingTerritory = attackingTerritory;
    }

    public int getDefendingTerritory() {
        return defendingTerritory;
    }

    public void setDefendingTerritory(int defendingTerritory) {
        this.defendingTerritory = defendingTerritory;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public void setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
    }

    @Override
    public Object clone() {
        StateActions cloned = null;
        try {
            cloned = (StateActions) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;
    }

}

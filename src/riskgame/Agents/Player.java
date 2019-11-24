package riskgame.Agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {

    private int turn;
    private int numberOfTroops = 50;
    private HashSet<Integer> territories = new HashSet<>();
    private int bonusTroops;

    public Player(int turn) {
        this.bonusTroops = 0;
        this.turn = turn;
    }

    public int getBonusTroops() {
        return bonusTroops;
    }

    public void setBonusTroops(int bonusTroops) {
        this.bonusTroops = bonusTroops;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getNumberOfTroops() {
        return numberOfTroops;
    }

    public void setNumberOfTroops(int numberOfTroops) {
        this.numberOfTroops = numberOfTroops;
    }

    public HashSet<Integer> getTerritories() {
        return territories;
    }

    public void setTerritories(HashSet<Integer> territories) {
        this.territories = territories;
    }

    public void addTerritory(int territory) {
        territories.add(territory);
    }

    public void addTroops(int troops) {
        this.numberOfTroops += troops;
    }

    public Object clone(State state) {
        Player cloned = null;
        try {
            cloned = (Player) super.clone();
            cloned.setTerritories(cloneHashSet(cloned.getTerritories()));
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cloned;

    }
    public ArrayList<Territory> getAttackableNeighbours(Territory territory, State state) {
        int[] neighbours = territory.getNeighbours();
        ArrayList<Territory> attackableNeighbours = new ArrayList<>();
        for (int number : neighbours) {
            if (isEnemy(state.getTerritories().get(number - 1))) {
                attackableNeighbours.add(state.getTerritories().get(number - 1));
            }
        }
        return attackableNeighbours;
    }

    private boolean isEnemy(Territory t) {
        boolean enemy = true;
        for (int territory : this.getTerritories()) {
            if (t.getNumber() == territory) {
                enemy = false;
            }
        }
        return enemy;
    }

    private static HashSet<Integer> cloneHashSet(HashSet<Integer> territories) {
        HashSet<Integer> set = new HashSet<>();
        territories.stream().forEach((t) -> {
            set.add(t);
        });
        return set;
    }

    public void divideTroopsRandom(State state) {
        ArrayList<Integer> s = new ArrayList<>(state.getPlayers().get(this.getTurn()).getTerritories());
        for (int i = 0; i < s.size(); i++) {
            state.getTerritories().get(s.get(i)-1).setNumberOfTroops(1);
            state.getPlayers().get(this.getTurn()).setBonusTroops(state.getPlayers().get(this.getTurn()).getBonusTroops()-1);
        }
        
        while(state.getPlayers().get(this.getTurn()).getBonusTroops()>0){
            int rndm = (int) Math.round(Math.random() * (state.getPlayers().get(this.getTurn()).getTerritories().size() - 1));
            state.getTerritories().get(s.get(rndm) - 1).setNumberOfTroops(state.getTerritories().get(s.get(rndm) - 1).getNumberOfTroops() + 1);
              state.getPlayers().get(this.getTurn()).setBonusTroops(state.getPlayers().get(this.getTurn()).getBonusTroops()-1);
        }
        
    }

    public Territory getTerritoryWithLowestTroops(State state) {
        int min = Integer.MAX_VALUE;
        Territory lowestTerritory = null;
        for (Integer territory : this.territories) {
            if (min == 0) {
                break;
            }
            if (state.getTerritories().get(territory).getNumberOfTroops() < min) {
                lowestTerritory = state.getTerritories().get(territory);
                min = state.getTerritories().get(territory).getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }

    public Territory getTerritoryWithHighestTroops(State state) {
        int max = Integer.MIN_VALUE;
        Territory highestTerritory = null;
        for (Integer territory : this.territories) {
            if (state.getTerritories().get(territory).getNumberOfTroops() > max) {
                highestTerritory = state.getTerritories().get(territory);
                max = state.getTerritories().get(territory).getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public abstract State play(State state);

}

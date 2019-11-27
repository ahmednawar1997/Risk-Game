package riskgame.Agents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import riskgame.Heuristic;
import riskgame.State;
import riskgame.Territory;

public abstract class Player implements Cloneable {

    private int turn;
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
        if(bonusTroops<3){
            bonusTroops=3;
        }
        this.bonusTroops = bonusTroops;
    }

    public void addBonusTroops() {
        bonusTroops += territories.size() / 3;
         if(bonusTroops<3){
            bonusTroops=3;
        }
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
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

    public void divideTroopsRandom(State state, int numberOfTroops) {
        int numberOfTerritories = this.territories.size();
        for (int num : this.territories) {
            state.getTerritories().get(num - 1).setNumberOfTroops(1);
            numberOfTroops--;
        }
        ArrayList<Integer> ts = new ArrayList<>(this.getTerritories());
        for (int i = 0; i < numberOfTroops; i++) {
            int rndm = (int) Math.round(Math.random() * (numberOfTerritories - 1));
            state.getTerritories().get(ts.get(rndm) - 1).setNumberOfTroops(state.getTerritories().get(ts.get(rndm) - 1).getNumberOfTroops() + 1);
//            System.out.println("number of troops "+ts.get(rndm));
        }

    }

    public Territory getTerritoryWithLowestTroops(State state) {
        int min = Integer.MAX_VALUE;
        Territory lowestTerritory = null;
        for (Integer territory : this.territories) {
            if (min == 0) {
                break;
            }
            if (state.getTerritories().get(territory - 1).getNumberOfTroops() < min) {
                lowestTerritory = state.getTerritories().get(territory - 1);
                min = state.getTerritories().get(territory - 1).getNumberOfTroops();
            }
        }
        return lowestTerritory;
    }

    public Territory getTerritoryWithHighestTroops(State state) {
        int max = Integer.MIN_VALUE;
        Territory highestTerritory = null;
        for (Integer territory : this.territories) {
            if (state.getTerritories().get(territory - 1).getNumberOfTroops() > max && getAttackableNeighbours(state.getTerritories().get(territory - 1), state).size() > 0) {
                highestTerritory = state.getTerritories().get(territory - 1);
                max = state.getTerritories().get(territory - 1).getNumberOfTroops();
            }
        }
        return highestTerritory;
    }

    public int getNumberOfTroops(State state) {
        int troops = 0;
        for (int i : this.getTerritories()) {
            troops += state.getTerritories().get(i - 1).getNumberOfTroops();
        }
        return troops;
    }

    protected void attack(Territory territory, Territory enemyTerritory, int numberOfAttacking, State newState) {
        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));
        System.out.println("Player " + attackingPlayer.getTurn() + " attacking");
        System.out.println("Troops Attacking: " + territory.getNumberOfTroops() + " vs " + enemyTerritory.getNumberOfTroops());
        if (territory.getNumberOfTroops() > enemyTerritory.getNumberOfTroops()) {
            System.out.println("Player " + attackingPlayer.getTurn() + " won " + enemyTerritory.getNumber() + " with Territory " + territory.getNumber());

            enemyTerritory.setNumberOfTroops(numberOfAttacking);
            territory.setNumberOfTroops(1);

            attackingPlayer.getTerritories().add(enemyTerritory.getNumber());
            defendingPlayer.getTerritories().remove(enemyTerritory.getNumber());

        } else {
            System.out.println("Player " + defendingPlayer.getTurn() + " won " + territory.getNumber() + " with Territory " + enemyTerritory.getNumber());

            territory.setNumberOfTroops(0);
            newState.getPlayers().get(enemyTerritory.getOwner(newState)).getTerritories().add(territory.getNumber());

            newState.getPlayers().get(territory.getOwner(newState)).getTerritories().remove(territory.getNumber());
        }
    }

    protected boolean attack(Territory territory, Territory enemyTerritory, State newState) {
        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));

        int attackingNumber = calculateAttackingNumber(territory);
        int defendingNumber = calculateDefendingNumber(enemyTerritory);

        System.out.println("Player " + attackingPlayer.getTurn() + " attacking");
        System.out.println("Troops Attacking: " + attackingNumber + " vs " + defendingNumber);
        System.out.println("Total Troops in Attacking: " + territory.getNumberOfTroops() + " vs " + enemyTerritory.getNumberOfTroops());
        System.out.println("Attacking " + territory.getNumber() + " on " + enemyTerritory.getNumber());

        if (isAttackingWon(attackingNumber, defendingNumber)) {
            enemyTerritory.setNumberOfTroops(enemyTerritory.getNumberOfTroops() - defendingNumber);

            System.out.println("attacking won");
            if (enemyTerritory.getNumberOfTroops() == 0) {
                System.out.println("Player " + attackingPlayer.getTurn() + " won " + enemyTerritory.getNumber() + " with Territory " + territory.getNumber());
                 
                conquer(territory, enemyTerritory, attackingPlayer, defendingPlayer);
                return true;
            }

        } else {
            System.out.println("defending won");

            territory.setNumberOfTroops(territory.getNumberOfTroops() - attackingNumber);
        }

        return false;
    }

    protected int calculateAttackingNumber(Territory territory) {
        if (territory.getNumberOfTroops() > 3) {
            return 3;
        } else {
            return (territory.getNumberOfTroops() - 1);
        }
    }

    protected int calculateDefendingNumber(Territory territory) {
        if (territory.getNumberOfTroops() >= 2) {
            return 2;
        } else {
            return territory.getNumberOfTroops();
        }
    }

    protected boolean isAttackingWon(int attackingNumber, int defendingNumber) {

        double rndm = Math.random();
        double prob = (double) attackingNumber / (double) (attackingNumber + defendingNumber);
        return (rndm < prob);

    }

    protected void conquer(Territory attck, Territory dfnd, Player attackingPlayer, Player defendingPlayer) {
        dfnd.setNumberOfTroops(attck.getNumberOfTroops() - 1);
        attck.setNumberOfTroops(1);
        attackingPlayer.getTerritories().add(dfnd.getNumber());
        defendingPlayer.getTerritories().remove(dfnd.getNumber());
    }

    protected State placeTroops(int t, State temp) {
//        State temp = (State) state.clone();
        temp.getTerritories().get(t - 1).setNumberOfTroops(temp.getTerritories().get(t - 1).getNumberOfTroops() + temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops());
        temp.getPlayers().get(temp.getPlayerTurn()).setBonusTroops(0);
        return temp;
    }

    protected State evaluatePlacement(State newState) {
        ArrayList<Integer> mytert = new ArrayList<>(newState.getPlayers().get(newState.getPlayerTurn()).getTerritories());
        double max = Heuristic.evaluateTerritory(mytert.get(0), newState);
        int territory = mytert.get(0);

        for (int i = 1; i < mytert.size(); i++) {
            double m = Heuristic.evaluateTerritory(mytert.get(i), newState);

            if (m > max) {
                territory = mytert.get(i);
                max = m;
            }
        }
        State temp = (State) newState.clone();
        temp.getGui().data.add("Placing " + temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops() + " troops on " + territory);
        System.out.println("Placing " + temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops() + " troops on " + territory);
        return placeTroops(territory, temp);
    }

    public abstract State play(State state);

}

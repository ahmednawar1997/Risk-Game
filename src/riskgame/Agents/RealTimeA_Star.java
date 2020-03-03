package riskgame.Agents;

import com.sun.javafx.util.TempState;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import riskgame.Heuristic;
import riskgame.State;
import riskgame.Territory;
import riskgame.StateActions;

public class RealTimeA_Star extends Player {

    private PriorityQueue<State> heap;
    private int exploredCount = 0;
    private int maxDepth;
    private final double aggroThreshhold;
    private boolean isEndTurn = false;

    public RealTimeA_Star(int turn, double aggroThreshhold) {
        super(turn);
        this.heap = new PriorityQueue<>();
        this.aggroThreshhold = aggroThreshhold;
    }

    @Override
    public State play(State state) {

        isEndTurn = false;
        this.heap = new PriorityQueue<>();
        state.setPlayerTurn(this.getTurn());

        State finalState = (State) state.clone();
        finalState = getBestPlacement(finalState);

        if (finalState.getPlayers().get(this.getTurn()).getTerritories().isEmpty()) {
            return finalState;
        }
        while (!isEndTurn) {

            State tempState = null;
            double bestHn = Double.MAX_VALUE;

            for (int i = 1; i < 10; i++) {

                System.out.println("root Hn:" + Heuristic.calculateHeuristic(finalState.getPlayers().get(finalState.getPlayerTurn()), finalState));
                finalState.setPreviousState(null);
                System.out.println("RealTimeA_Star Thinking...");

                maxDepth = i;
                finalState = getShortestPathToGoal(finalState);

                double tempHn = Heuristic.calculateHeuristic(finalState.getPlayers().get(finalState.getPlayerTurn()), finalState);
                if (tempHn < bestHn) {
                    tempState = finalState;
                    bestHn = tempHn;
                }
                System.out.println("lowest Hn:" + bestHn);
                System.out.println("tempHn: " + tempHn);
            }
            finalState = tempState;
            System.out.println("RealTimeA_Star Found Fastest Route");

            if (isEndTurn) {
                finalState.getActions().setEndTurn(true);
                System.out.println("RealTimeA_Star Ended Turnnn");
                return finalState;
            }
            State afterRootState = getAfterRootState(finalState);
            State root = afterRootState.getPreviousState();

            Territory attackingTerritory = afterRootState.getTerritories().get(afterRootState.getActions().getAttackingTerritory() - 1);
            Territory defendingTerritory = afterRootState.getTerritories().get(afterRootState.getActions().getDefendingTerritory() - 1);

            attackingTerritory = root.getTerritories().get(attackingTerritory.getNumber() - 1);
            defendingTerritory = root.getTerritories().get(defendingTerritory.getNumber() - 1);

//            Utils.printState(root);
            finalState = attackUntilConquer(attackingTerritory, defendingTerritory, root);

        }

        System.out.println("RealTimeA_Star Ended Turn");
        heap = null;
        return finalState;
    }

    private State attackUntilConquer(Territory attackingTerritory, Territory defendingTerritory, State root) {
        boolean conquered = false;
        while (!conquered) {
            if (attackingTerritory.getNumberOfTroops() == 1) {
                break;
            }
            conquered = attack(attackingTerritory, defendingTerritory, root);
        }
        return root;

    }

    private State getShortestPathToGoal(State state) {
        isEndTurn = true;
        heap.add(state);
        double Hn = 0;
        exploredCount = 0;
        State newState = null;
        int Considered = 0;
        int notConsidered = 0;
        while (!heap.isEmpty()) {
//            Utils.printHeap(heap);
//            System.out.println("Heap: "+heap.peek().getCost());

            newState = (State) heap.poll().clone();
            exploredCount++;
//            System.out.println("Explored: " + exploredCount);
//            System.out.println("Exploring depth: " + newState.getDepth());

            if (isGoal(newState) || newState.getDepth() >= maxDepth) {
                System.out.println("Found next move in RealTimeA_Star");
                return newState;
            }
            newState.setDepth(newState.getDepth() + 1);

            for (int number : newState.getPlayers().get(this.getTurn()).getTerritories()) {
                Territory territory = newState.getTerritories().get(number - 1);
                ArrayList<Territory> attackableNeighbours = newState.getPlayers().get(newState.getPlayerTurn()).getAttackableNeighbours(territory, newState);

                for (Territory enemyNeigbour : attackableNeighbours) {
                    if (territory.getNumberOfTroops() > 2) {
                        State tempState = simulateAttack(territory, enemyNeigbour, aggroThreshhold, newState);
                        if (tempState != null) {
                            tempState.setActions(new StateActions(territory.getNumber(), enemyNeigbour.getNumber()));
                            tempState.setPreviousState(newState);
                            isEndTurn = false;
                            Hn = Heuristic.calculateHeuristic(tempState.getPlayers().get(tempState.getPlayerTurn()), tempState);
                            tempState.setCost(Hn + tempState.getDepth());
//                            System.out.println("cost:" + Hn + tempState.getDepth());
                            heap.add(tempState);
//                            System.out.println("Considered:"+ ++Considered);

                        } else {

//                            System.out.println("Not Considered:"+ ++notConsidered);
                        }
                    }
                }
            }
        }
        return newState;

    }

    private boolean isGoal(State newState) {
        if (newState.getPlayers().get(this.getTurn()).getTerritories().size() == 50) {
            System.out.println("Found Goal");
            return true;
        }
        return false;

    }

    protected State simulateAttack(Territory atckTerritory, Territory dfndTerritory, double threshhold, State state) {

        State newState = (State) state.clone();
        Territory territory = newState.getTerritories().get(atckTerritory.getNumber() - 1);
        Territory enemyTerritory = newState.getTerritories().get(dfndTerritory.getNumber() - 1);

        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));

        if (isAttackingWonAboveThreshhold(territory, enemyTerritory, threshhold)) {
            conquer(territory, enemyTerritory, attackingPlayer, defendingPlayer);
        } else {
            newState = null;
        }

        return newState;
    }

    protected boolean isAttackingWonAboveThreshhold(Territory attackingTerritory, Territory defendingTerritory, double aggroThreshhold) {
        if (attackingTerritory.getNumberOfTroops() * 1.0 / defendingTerritory.getNumberOfTroops() * 1.0 > aggroThreshhold) {
            return true;
        }
        return false;
    }

    private State getBestPlacement(State newState) {
        double max = Integer.MIN_VALUE;
        Territory territory = null;
        for (int number : newState.getPlayers().get(newState.getPlayerTurn()).getTerritories()) {

            double m = Heuristic.BSR(newState.getTerritories().get(number - 1), newState);

            if (m > max) {
                territory = newState.getTerritories().get(number - 1);
                max = m;
            }
        }
        State temp = (State) newState.clone();
        System.out.println("Placing " + temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops() + " troops on " + territory.getNumber());
        return placeTroops(territory.getNumber(), temp);
    }

    private State getAfterRootState(State temp) {
        State newState = (State) temp.clone();
        Stack<State> stack = new Stack<>();
        while (newState.getPreviousState() != null) {
            stack.add(newState);
            newState = newState.getPreviousState();
        }

        return stack.pop();
    }

    protected boolean attack(Territory territory, Territory enemyTerritory, State newState) {

        Player attackingPlayer = newState.getPlayers().get(territory.getOwner(newState));
        Player defendingPlayer = newState.getPlayers().get(enemyTerritory.getOwner(newState));

        int attackingNumber = calculateAttackingNumber(territory);
        int defendingNumber = calculateDefendingNumber(enemyTerritory);

        System.out.println("**************RealTimeA_Star Attacking*******************");
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

}

package riskgame.Agents;

import java.util.ArrayList;
import java.util.HashSet;
import riskgame.Heuristic;
import riskgame.RiskGame;
import riskgame.State;
import riskgame.Territory;
import sun.net.www.http.Hurryable;

public class MiniMax extends Player {

    public MiniMax(int turn) {
        super(turn);
    }

    @Override
    public State play(State state) {
        state.setPlayerTurn(this.getTurn());
        State newState = (State) state.clone();
        System.out.println("Minmax Turn: ");
        State placement = evaluatePlacement(newState);
    
        placement.getPlayers().get(placement.getPlayerTurn()).setBonusTroops(0);
        State a = (State) placement.clone();
        State b = evaluateAttack(a);

        return b;

    }

    int max(State state, int alpha, int beta) {
        //  System.gc();
        
        if (RiskGame.isGameEnded(state)||state.getDepth()==10) {
            //   System.out.println(Heuristic.evaluateUtility(state));
            return Heuristic.evaluateUtility(state);
        }
        State temp = (State) state.clone();
        if (state.getPlayers().get(0).getBonusTroops() > 0) {//assign troops

            ArrayList<Integer> mytert = new ArrayList<>(state.getPlayers().get(state.getPlayerTurn()).getTerritories());
            double max = Heuristic.evaluateTerritory(mytert.get(0), state);
            int territory = mytert.get(0);

            for (int i = 1; i < mytert.size(); i++) {
                double m = Heuristic.evaluateTerritory(mytert.get(i), state);

                if (m > max) {
                    territory = mytert.get(i);
                }
            }

            temp = placeTroops(territory, temp);
        }//attack
        temp.setDepth(state.getDepth() + 1);
        State newstate = (State) temp.clone();

        ArrayList<Integer> mytert = new ArrayList<>(newstate.getPlayers().get(newstate.getPlayerTurn()).getTerritories());
        newstate.setPlayerTurn(1);
        newstate.getPlayers().get(1).setBonusTroops(newstate.getPlayers().get(1).getTerritories().size() / 3);
        newstate.setDepth(temp.getDepth() + 1);

        alpha = min(temp, alpha, beta); //endturn

        for (int i = 0; i < mytert.size(); i++) {
            newstate = (State) temp.clone();
            Territory t = newstate.getTerritories().get(mytert.get(i) - 1);

            if (t.getNumberOfTroops() > 1) {

                ArrayList<Territory> neighbors = getAttackableNeighbours(t, newstate);
                for (int j = 0; j < neighbors.size(); j++) {

                    if (Heuristic.evaluateAttack(t.getNumber(), neighbors.get(j).getNumber(), temp) >= 2.1) {

                        State w = simulateWin(t.getNumber(), neighbors.get(j).getNumber(), (State) temp.clone());
                        double cost = max(w, alpha,beta);
                        if (cost > alpha) {
                            alpha = (int) cost;

                        }

                        if (alpha >= beta) {
                            return beta;
                        }
                    }
                }
            }
        }
        return alpha;

    }

    int min(State state, int alpha, int beta) {
        if (RiskGame.isGameEnded(state)||state.getDepth()==10) {
            //   System.out.println(Heuristic.evaluateUtility(state));
            return Heuristic.evaluateUtility(state);
        }
        State temp = (State) state.clone();
        if (state.getPlayers().get(state.getPlayerTurn()).getBonusTroops() > 0) {//assign troops

            ArrayList<Integer> mytert = new ArrayList<>(state.getPlayers().get(state.getPlayerTurn()).getTerritories());
            double max = Heuristic.evaluateTerritory(mytert.get(0), state);
            int territory = mytert.get(0);

            for (int i = 1; i < mytert.size(); i++) {
                double m = Heuristic.evaluateTerritory(mytert.get(i), state);

                if (m > max) {
                    territory = mytert.get(i);
                }
            }

            temp = placeTroops(territory, temp);
        }//attack
        temp.setDepth(state.getDepth() + 1);
        State newstate = (State) temp.clone();

        ArrayList<Integer> mytert = new ArrayList<>(newstate.getPlayers().get(newstate.getPlayerTurn()).getTerritories());
        newstate.setPlayerTurn(0);
        newstate.getPlayers().get(0).setBonusTroops(newstate.getPlayers().get(0).getTerritories().size() / 3);
        newstate.setDepth(temp.getDepth() + 1);

        beta = max(temp, alpha, beta); //endturn

        for (int i = 0; i < mytert.size(); i++) {
            newstate = (State) temp.clone();
            Territory t = newstate.getTerritories().get(mytert.get(i) - 1);

            if (t.getNumberOfTroops() > 1) {

                ArrayList<Territory> neighbors = getAttackableNeighbours(t, newstate);
                for (int j = 0; j < neighbors.size(); j++) {

                    if (Heuristic.evaluateAttack(t.getNumber(), neighbors.get(j).getNumber(), temp) >= 2.1) {

                        State w = simulateWin(t.getNumber(), neighbors.get(j).getNumber(), (State) temp.clone());
                        double cost = min(w, alpha, beta);
                        if (cost < beta) {
                            beta = (int) cost;

                        }

                        if (alpha >= beta) {
                            return alpha;
                        }
                    }
                }
            }
        }
        return beta;

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

    private State placeTroops(int t, State state) {
        State temp = (State) state.clone();
        temp.getTerritories().get(t - 1).setNumberOfTroops(temp.getTerritories().get(t - 1).getNumberOfTroops() + temp.getPlayers().get(state.getPlayerTurn()).getBonusTroops());
        temp.getPlayers().get(temp.getPlayerTurn()).setBonusTroops(0);
        return temp;
    }

    private State evaluatePlacement(State newState) {
        ArrayList<Integer> mytert = new ArrayList<>(newState.getPlayers().get(newState.getPlayerTurn()).getTerritories());
        double max = Heuristic.evaluateTerritory(mytert.get(0), newState);
        int territory = mytert.get(0);

        for (int i = 1; i < mytert.size(); i++) {
            double m = Heuristic.evaluateTerritory(mytert.get(i), newState);

            if (m > max) {
                territory = mytert.get(i);
            }
        }
        State temp = (State) newState.clone();
        System.out.println("Placing "+temp.getPlayers().get(temp.getPlayerTurn()).getBonusTroops()+" troops on "+territory);
        return placeTroops(territory, temp);
    }

    private State simulateWin(int number, int number0, State temp) {
        State state = (State) temp.clone();
        if (state.getTerritories().get(number0 - 1).getNumberOfTroops() > 2) {
            state.getTerritories().get(number0 - 1).setNumberOfTroops(state.getTerritories().get(number0 - 1).getNumberOfTroops() - 2);
        } else {
            state.getTerritories().get(number0 - 1).setNumberOfTroops(state.getTerritories().get(number - 1).getNumberOfTroops() - 1);
            state.getTerritories().get(number - 1).setNumberOfTroops(1);
            state.getPlayers().get(getOwner(state, number0)).getTerritories().remove(number0);
            state.getPlayers().get(state.getPlayerTurn()).getTerritories().add(number0);

        }
        return state;
    }

    public int getOwner(State state, int num) {
        for (int i = 0; i < state.getPlayers().size(); i++) {
            for (int j = 0; j < state.getPlayers().get(i).getTerritories().size(); j++) {
                if (state.getPlayers().get(i).getTerritories().contains(num)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private State simulateLoss(int number, int number0, State temp) {
        State state = (State) temp.clone();
        if(state.getTerritories().get(number-1).getNumberOfTroops()>3){
            state.getTerritories().get(number-1).setNumberOfTroops(state.getTerritories().get(number-1).getNumberOfTroops()-3);
            
        }else if(state.getTerritories().get(number-1).getNumberOfTroops()==3){
             state.getTerritories().get(number-1).setNumberOfTroops(state.getTerritories().get(number-1).getNumberOfTroops()-2);
        }else if(state.getTerritories().get(number-1).getNumberOfTroops()==2){
            state.getTerritories().get(number-1).setNumberOfTroops(state.getTerritories().get(number-1).getNumberOfTroops()-1);
        }

        return state;
    }

    private int[] CalculateAttack(State a) {
        int alpha = Integer.MIN_VALUE;
        int beta = -Integer.MAX_VALUE;
        int[] s = new int[2];
        s[0] = -1;

        ArrayList<Integer> mytert = new ArrayList<>(a.getPlayers().get(a.getPlayerTurn()).getTerritories());

        //System.out.println("Considring Attacking number 0...............");
        //  double max = max(temp, Integer.MIN_VALUE, Integer.MAX_VALUE);
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < mytert.size(); i++) {
            State temp = (State) a.clone();

            Territory t = temp.getTerritories().get(mytert.get(i) - 1);
            if (t.getNumberOfTroops() > 1) {
                ArrayList<Territory> neighbors = getAttackableNeighbours(t, temp);
                for (int j = 0; j < neighbors.size(); j++) {

                    if (Heuristic.evaluateAttack(t.getNumber(), neighbors.get(j).getNumber(), temp) >= 2.1) {

                        State w = simulateWin(t.getNumber(), neighbors.get(j).getNumber(), (State) temp.clone());
                        double cost = max(w, Integer.MIN_VALUE, Integer.MAX_VALUE);
                        if (cost > max) {
                            max = cost;
                            s[0] = t.getNumber();
                            s[1] = neighbors.get(j).getNumber();
                        }
                    }
                }
            }
        }
        return s;
    }

    private State evaluateAttack(State a) {
        State temp = (State) a.clone();
        int s[] = CalculateAttack(a);
        if (s[0] == -1) {
            System.out.println("Ending Turn.......");
            RiskGame.printState(temp);
            return temp;
        } else {
            State f = (State) temp.clone();

            if (f.getTerritories().get(s[0] - 1).getNumberOfTroops() == 1) {
                System.out.println("Lost war....");
                return f;
            }
            System.out.println("Declaring attack from" + s[0] + " on " + s[1] + ".......");
            double dice = Math.random();

            if (dice < 0.5) {
                System.out.println("Lost Battle.......");
                f = simulateLoss(s[0], s[1], f);
                RiskGame.printState(f);
            } else {
                System.out.println("Won Battle.......");
                f = simulateWin(s[0], s[1], f);
                RiskGame.printState(f);
                if (f.getPlayers().get(f.getPlayerTurn()).getTerritories().contains(s[1])) {
                    System.out.println("Won the war.....");
                    return f;
                }
            }

            return evaluateAttack(f);

        }
    }

    

}

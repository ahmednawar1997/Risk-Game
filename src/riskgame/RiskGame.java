/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

import java.util.ArrayList;
import riskgame.Agents.MiniMax;
import riskgame.Agents.Player;

public class RiskGame {

    public static void main(String[] args) {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
        Player player1 = new MiniMax();
        Player player2 = new MiniMax();

        State state = new State();
        
    }

}

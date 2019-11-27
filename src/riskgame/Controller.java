/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgame;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import riskgame.Agents.Player;

/**
 *
 * @author Shams Sherif
 */
public class Controller {
  static  ArrayList<Color> colors= new ArrayList<>();
    
    public Controller(){
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
    }
    
    
    public static void Recolor(Scene s, State state){
        Group root= (Group) s.getRoot();
        
        ArrayList<Player> p = state.getPlayers();
        for (int i = 0; i < p.size(); i++) {
            
            ArrayList<Integer> t= new ArrayList<>(p.get(i).getTerritories());
            for (int j = 0; j < t.size(); j++) {
                SVGPath svg= (SVGPath) root.getChildren().get(t.get(j)-1);
                svg.setFill(colors.get(i));
            }
        }
    }
}

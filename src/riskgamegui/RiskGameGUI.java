package riskgamegui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;

import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import riskgame.Agents.A_Star;
import riskgame.Agents.Aggressive;
import riskgame.Agents.MiniMax;
import riskgame.Agents.Pacifist;
import riskgame.Agents.Passive;
import riskgame.Agents.Player;
import riskgame.Controller;
import riskgame.RiskGame;
import riskgame.State;
import riskgame.Territory;
import riskgame.Utils;

public class RiskGameGUI extends Application {

    Scene scene;
 Text text;
 public static final ObservableList names = 
        FXCollections.observableArrayList();
    public static final ObservableList data = 
        FXCollections.observableArrayList();
  Text Details;
    ListView listView;
    @Override
    public void start(Stage primaryStage)  {
        
        Group root = new Group();
        SVGPath svgPath = new SVGPath();
Group statGroup= new Group();
scene = new Scene(root, 1400, 1000);
         text = new Text("Turn Number: 0");
           listView = new ListView(data);
           
        
        listView.setEditable(true);
        
       
         
       
          
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));  
        
        
        Text Details = new Text("Turn Number: 0");
        text.setFill(Color.RED);
        statGroup.getChildren().add(text);
       
        listView.setLayoutY(100);
        listView.setLayoutX(1100);
        statGroup.setLayoutY(900);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        for (String path : Map.getMap()) {
            svgPath = new SVGPath();
            svgPath.setContent(path);
            // svgPath.setP;
            svgPath.setFill(Color.BISQUE);

            root.getChildren().add(svgPath);
        }
        root.scaleXProperty().add(100);
        root.getChildren().add(statGroup);
        root.getChildren().add(listView);
        SVGPath svg = (SVGPath) root.getChildren().get(48);
        svg.setFill(Color.BLACK);
        Singleton sin= Singleton.getInstance();
        sin.setS(scene);
        
        RiskGame game = new RiskGame();
        
        primaryStage.setTitle("Risk");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Thread.sleep(5000);
        //  System.out.println("dsaasdasddasdasdsads");
      // initGame();
       Thread t = new Thread(){
           @Override
           public void run() {
             initGame();
           }
           
       };
       t.start();
    }

    public static void main(String[] args) {
      
        System.out.println("asdasddasdasdasdasdasf");
       launch(args);
        
       // new RiskGameGUI().initGame();

    }
    static ArrayList<Color> colors = new ArrayList<>();

    public boolean initGame() {
        Utils utils = new Utils();

        ArrayList<Territory> usaTerritories = utils.initUSA();
       // Player player1 = new MiniMax(0,7,2.6,true);
    Player player1 = new MiniMax(0, 6, 2.5, true);
        Player player2= new Passive(1);

        //Player player3 = new Aggressive(2);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        // players.add(player3);
        utils.divideTerritoriesRandom(players, usaTerritories);

        State state = new State(usaTerritories, players, 0, this);
        utils.divideTroops(state);

        Utils.printState(state);
        
        Recolor(state);
        
      
        startGame(state);
        return true;
    }

    private void startGame(State state) {
        int turns = 0;
        while (true) {
            turns++;

            for (int i = 0; i < state.getPlayers().size(); i++) {
                Utils.printState(state);
 //Recolor(state);
                if (isGameEnded(state)) {
                     Recolor(state);
                    System.out.println("Turns = " + turns);
                    return;
                }
                Group g = (Group)scene.getRoot();
                Group f = (Group) g.getChildren().get(50);
               // data.add("Turn has passed");
                
                text.setText("\tTurn Number: "+turns+"\n\tTurn of: "+state.getPlayers().get(i).getClass().getSimpleName());
                state.setPlayerTurn(i);
                state.getPlayers().get(i).addBonusTroops();
                state = state.getPlayers().get(i).play(state);
                System.out.println("*************************************************************");
            }
        }

    }

    public boolean isGameEnded(State state) {
        int numOfSurviving = 0;
        for (Player player : state.getPlayers()) {

            if (!player.getTerritories().isEmpty()) {

                numOfSurviving++;

            }
        }
        if (numOfSurviving == 1) {
            data.add("Game Over");
            return true;
        }
        return false;
    }

    public static void printState(State temp) {
        ArrayList<Integer> s = new ArrayList<>(temp.getPlayers().get(0).getTerritories());
        System.out.println("MinMax ownes: ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
        }
        System.out.println("");

        s = new ArrayList<>(temp.getPlayers().get(1).getTerritories());
        System.out.println("Pssive ownnes: ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print("Territory number: " + s.get(i) + " with " + temp.getTerritories().get(s.get(i) - 1).getNumberOfTroops() + " troops");
        }
        System.out.println("");
    }

    public void Recolor(State state) {
        Group root = (Group) scene.getRoot();

        ArrayList<Player> p = state.getPlayers();
        for (int i = 0; i < p.size(); i++) {

            ArrayList<Integer> t = new ArrayList<>(p.get(i).getTerritories());
            for (int j = 0; j < t.size(); j++) {
                SVGPath svg = (SVGPath) root.getChildren().get(t.get(j) - 1);
                svg.setFill(colors.get(i));
                svg.setStroke(Color.BLACK);
            }
        }
    }
}

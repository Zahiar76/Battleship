/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class GameVScomputerController implements Initializable {
   private Battleship manager;
    private Player player = new Player();
    private Player computer = new Player();
    private Computer computerLogic = new Computer();
    public static ArrayList<Integer> random = new ArrayList<>();

    
    @FXML
    GridPane opponentMap = new GridPane();
    
    @FXML
    HBox opponentHBox = new HBox();
    
    @FXML
    VBox opponentVBox = new VBox();
    
    //Maps
    @FXML
    GridPane playerMap = new GridPane();
    
    @FXML
    HBox playerHBox = new HBox();
    
    @FXML
    VBox playerVBox = new VBox();
    
    /**
     * Initializes the controller class.
     */
    
    public void createMap(){
        Button[] putShips = {new Button(),new Button(), new Button(), new Button()};
        Label[] toSetLabels = {new Label(),new Label(),new Label(),new Label()};
        addToArray();
        computerLogic.probabilityToHit = 50.0;
        //computer.getMap().createMap(opponentMap, opponentVBox, opponentHBox,putShips, toSetLabels, player);
       playerMap.getChildren().clear();
       manager.getPlayer1().getMap().setMap(playerMap, playerVBox, playerHBox);
       
       computer.getMap().createMap(opponentMap, opponentVBox, opponentHBox,putShips, toSetLabels, player);
       computer.getMap().setShipsComputer(computer);
       
       opponentMap.addEventFilter(MouseEvent.MOUSE_PRESSED, (e) ->{
         if(e.isPrimaryButtonDown()){ 
          computerLogic.tryToHit(manager.getPlayer1());
         }
         });

       
    }
    private int random(){
        int randomNumber = new Random().nextInt(100);
        return random.get(randomNumber);
    }
    
    private void addToArray(){
        for(int index = 0; index < 99; index++){
            random.add(index);
        }
    }
    
    @FXML
    private void showDestroyedShips(ActionEvent event){
       //manager.getPlayer1().findDestroyedShip();
        computer.findDestroyedShip();
    }
    @FXML
    public void create(ActionEvent event){
        computer.getMap().setShipsComputer(computer); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }   
        
    public void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public GameVScomputerController getMe(){
        return this;
    }
    
    
}

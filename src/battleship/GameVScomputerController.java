/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class GameVScomputerController implements Initializable {
    Battleship manager;
    Player player = new Player();
    Player computer = new Player();
    
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
        //computer.getMap().createMap(opponentMap, opponentVBox, opponentHBox,putShips, toSetLabels, player);
       playerMap.getChildren().clear();
       manager.getPlayer1().getMap().setMap(playerMap, playerVBox, playerHBox);
       
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

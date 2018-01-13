/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class LevelOfDifficultyController implements Initializable {
    
    Battleship manager;
    
    @FXML
    private VBox menuPane = new VBox();
    
    @FXML
    private VBox mainPane = new VBox();
    
    //test
    
    @FXML
    private void showMenu(){
        
        mainPane.setDisable(true);
        mainPane.setOpacity(0.55);
        
        menuPane.setDisable(false);
        menuPane.setVisible(true);
        
    }
    
    @FXML
    private void closeMenu(){
        mainPane.setOpacity(100);
        mainPane.setDisable(false);
        
        menuPane.setVisible(false);
        menuPane.setDisable(true);
        
    }
    
    @FXML
    private void startGame(ActionEvent event) throws IOException{
        System.out.println("WOIRKING");
        manager.startGame();
        manager.getObjektGame().createMap();
    }
 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainPane.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()){
                System.out.println("Works");
                showMenu();
           
            }
            
        });
        menuPane.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if(KeyCode.ESCAPE == event.getCode()){
                System.out.println("Works");
                closeMenu();
           
            }
            
        });
    }   
    
    public void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public LevelOfDifficultyController getMe(){
        return this;
    }
    
}
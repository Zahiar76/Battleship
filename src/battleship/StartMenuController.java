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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Emil Poorando
 */
public class StartMenuController implements Initializable {
    /*
        Deklaration
    */
    Battleship manager;
    
    @FXML
    Button btnComp;
    
    /*
        Methoden
    */
    @FXML
    private void goToCreateShips(ActionEvent event) throws IOException{
        manager.putShipsWindow();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public StartMenuController getMe(){
        return this;
    }
    
}

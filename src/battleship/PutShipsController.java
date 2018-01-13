/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;


import java.io.IOException;
import static java.lang.Math.sqrt;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 * FXML Controller class
 *
 * @author Emil Poorando
 */
public class PutShipsController implements Initializable {
    /*
        Deklaration
    */
    Battleship manager;
    int groesse = 10;
    Field[][] map = new Field[groesse][groesse]; //Hier werden logisch felder gespeichert;
    Player player = new Player(); //Player wird erstellt
    Player computer = new Player(); // Computer wird erstellt
    int whichShip = 0;  // Was für ein Schiff wird eingesetzt

    

    
    @FXML
    VBox vbox = new VBox();
    
    @FXML
    HBox hbox = new HBox();
    
    @FXML
    GridPane mapGui = new GridPane();
    
    @FXML
    Button put1 = new Button(),put2= new Button(),put3= new Button(),put4= new Button(),readyBtn = new Button();
    
    @FXML
    Label toSetLabel1,toSetLabel2,toSetLabel3,toSetLabel4;
    


    
    Map map2 = new Map();
    
    /*
        Methoden
    */
    public void createMap(){
        Button[] putShips = {put1,put2,put3,put4};
        Label[] toSetLabels = {toSetLabel1,toSetLabel2,toSetLabel3,toSetLabel4};
        player.getMap().createMap(mapGui, vbox, hbox,putShips, toSetLabels, player);

    }

    //Schiff zum Einsetzten wird ausgewählt
    @FXML
    private void selectShip(ActionEvent event){
        int lastWhichShip = player.getMap().getWhichShip();

        
        if(event.getSource() == put4 && isShipComplete(lastWhichShip) == true){
            changeColorBtn(4,lastWhichShip);
            //changeColorBtn(4);
            player.getMap().setWhichShip(4); 
            
        }
        if(event.getSource() == put3 && isShipComplete(lastWhichShip) == true){
            //changeColorBtn(3);
            changeColorBtn(3,lastWhichShip);
            player.getMap().setWhichShip(3); 

          //  createMapForComputer();
            
        }
        if(event.getSource() == put2 && isShipComplete(lastWhichShip) == true){
           // changeColorBtn(2);
           changeColorBtn(2,lastWhichShip);
           player.getMap().setWhichShip(2); 

            //createMapForComputer();
        }
        if(event.getSource() == put1 && isShipComplete(lastWhichShip) == true){
           //  changeColorBtn(1);
           changeColorBtn(1,lastWhichShip);
            player.getMap().setWhichShip(1);  

           // createMapForComputer();
           
           
        }
    }
    
    
    private void changeColorBtn(int whichShip, int lastWhichShip){
        Button[] putShips = {put1,put2,put3,put4};
        if(lastWhichShip != 0){
            
            putShips[lastWhichShip - 1].getStyleClass().remove("putShipsAfter");
            putShips[lastWhichShip - 1].getStyleClass().add("putShipsBefore");

            putShips[whichShip - 1].getStyleClass().remove("putShipsBefore");
            putShips[whichShip - 1].getStyleClass().add("putShipsAfter");
            
        }else{
           putShips[whichShip - 1].getStyleClass().remove("putShipsBefore");
           putShips[whichShip - 1].getStyleClass().add("putShipsAfter"); 
        }
        
    }
    
    private boolean isShipComplete(int whichShip){

        if(whichShip != 0){
            for(ArrayList count : player.getShipsPosition2().get(whichShip - 1)){
                if(count.size() % (whichShip) != 0){
                    return false;
                }
            }
        }

        return true;
    }
    
    @FXML
    private void goToMenu(ActionEvent event){
      manager.goToMenu();
    }
    
    @FXML
    private void goToLevel(ActionEvent event) throws IOException{
        manager.goToLevel();
        manager.setPlayer1(player);
    }
//    
    @FXML
    private void test(){
        System.out.println("works");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int ready = 0;
        addListener();
        
        if(toSetLabel4.getText().equals("0")
         &&toSetLabel3.getText().equals("0")
         &&toSetLabel2.getText().equals("0")
         &&toSetLabel1.getText().equals("0")){
            System.out.println("2");
            readyBtn.setVisible(true);
}
           
    }    
    
    public void addListener(){
        Label[] toSetLabels = {toSetLabel1,toSetLabel2,toSetLabel3,toSetLabel4};
        final int ready = 0;
        for(Label label :toSetLabels){     
            label.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                    if(checkIFzero()){
                        readyBtn.setDisable(false);
                    }else{
                        readyBtn.setDisable(true);
                    }
                }
            }); 
        }
  
    }
    
    public boolean checkIFzero(){
        Label[] toSetLabels = {toSetLabel1,toSetLabel2,toSetLabel3,toSetLabel4};
        int index = 0;
          for(Label label :toSetLabels){     
            if(label.getText().equals("0")){
                index ++;
            }
          } 
          System.out.println("index "+index);
        if(index == 4){
            return true;
        }
        return false;
    }

    
    public void setMainApp(Battleship manager){
        this.manager = manager;
    }
    
    public PutShipsController getMe(){
        return this;
    }
    
}

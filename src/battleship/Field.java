/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.lang.String;
/**
 *
 * @author Emil Poorando
 */
public class Field {
    /*
        Deklaration
    */
    private int shipID; 
    private boolean ship = false; //Sie stellt fest, ob sich auf dem Feld ein Schiff befindet
    private Button btn = new Button(); //Grapische Oberfläche für ein Feld
    private int onTheEdge = 0;         //1 = oben 2 = rechts 3 = unten 4 = links 12 = oben rechts  14 = oben links 32 = unten rechts 34 = links
    private boolean isNeighbour = false;
    private boolean hitted = false;
    private boolean destroyed = false;
    private boolean checked = false;


    /*
        Konstruktor
    */
    Field(GridPane mapGui,int groesse, int position){
        String lokalisation = Integer.toString(position);
        if(position <= 9){
            lokalisation = "0" + lokalisation;
        }
    //Erstellt einen neuen Button
        btn.setId(lokalisation); // Setzt die ID des Buttons ein
        btn.setPrefWidth(mapGui.getPrefWidth() / groesse); //Die Breit der Karte wird durch die entsprechende Anzahl von Buttons geteilt, und somit hat jeder Button die gleiche Breite  
        btn.setPrefHeight(mapGui.getPrefHeight()  / groesse); //Die Höhe der Karte wird durch die entsprechende Anzahl von Buttons geteilt, damit jeder Button die gleiche Höhe bekommt 
        System.out.println(lokalisation);
        showNormal();
}
    Field(){
   
    }
    

    
    /*
        Methoden
    */
    
    //Getter und Setter
    

    public boolean getIsNeighbour() {
        return isNeighbour;
    }
    //HIER WAR ICH ZUM LETZTEN MAL, proboje dodac do kazdego nachbara jakas liczbe albo string, bo jak usuwam to niekotre strony sa gole i mozna tam postawic ->
    /*
        statek. pierwsza mysl byla zeby kazda nachabarn dostal id. Problem w tym ze jak usune starszy statek obok nowego, to ten nowy bd goly 
    */
    public void setIsNeighbour(boolean isNeighbour) {
        this.isNeighbour = isNeighbour;
        if(isNeighbour && !ship){
            btn.setStyle("-fx-background-color:green;");
        }else if(!isNeighbour && !ship){
      
          btn.setStyle("");  
        }
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship, int whichShip) {
        if(whichShip == 4){
            btn.setStyle("-fx-background-color:#DD1B1B;"); 
        }else if(whichShip == 3){
            btn.setStyle("-fx-background-color:#DD1B1B;");
        }else if(whichShip == 2){
            btn.setStyle("-fx-background-color:#DD1B1B;");
        }else if(whichShip == 1){
            btn.setStyle("-fx-background-color:#DD1B1B;");
        }else if(whichShip == 0){
             btn.setStyle("-fx-background-color:;");
             //setDestroyed(true);
        }
        this.ship = ship;
        
    }
    
    public void hideShip(){
        btn.setStyle("-fx-background-color:;");
    }
    public boolean getHitted() {
        return hitted;
    }

    public void setHitted(boolean hitted) {
        this.hitted = hitted; 
        if(hitted){
            btn.setStyle("-fx-background-color:#2B2B2B;");
            setChecked(true);
        }else{
            btn.setStyle("-fx-background-color:#000000;");
            setChecked(true);
        }
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
    
    public int getOnTheEdge() {
        return onTheEdge;
    }

    public void setOnTheEdge(int onTheEdge) {
        this.onTheEdge = onTheEdge;
    }
    
    public int getIDShip() {
        return shipID;
    }

    public void setIDShip(int shipID) {
        this.shipID = shipID;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
        btn.setStyle("-fx-background-color:#14d195");
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        btn.setStyle("-fx-background-color:#000000;");
        this.checked = checked;
    }
    

    
    //Weitere Methoden
    public String setNeighbour(){
        return "";
    }
    //Button wird als "Getroffen" markiert
    public void showHitted(){
        btn.setStyle("-fx-background-color:#2B2B2B");
        hitted = true;
     
    }
    //Button wird als "Nicht Getroffen" markiert
    public void showNoHitted(){
        
    }
    //Button wird als "Schiff" markiert
    public void showShip(){
        btn.setStyle("-fx-background-color:#14d195");
      
    }
    //Button wird als "Getroffen" markiert
    public void showNormal(){
        getBtn().setStyle("");
        setShip(false,0);
        getBtn().setStyle("");
        getBtn().getStyleClass().add("Field");
        shipID = 0;
    }
    public int getX(){
        int x = Integer.parseInt(btn.getId().toString().substring(1, 2)); //Position auf der X-Achse
        //System.out.println(y);
        return x;
    }
    public int getY(){
        int y = Integer.parseInt(btn.getId().toString().substring(0, 1)); //Position auf der Y-Achse
        //System.out.println(y);
        return y;
    }
    public int getShipID(){
       return Integer.parseInt(getBtn().getId());
    }

}
    
 
    
    
    
    
    
    
    
    


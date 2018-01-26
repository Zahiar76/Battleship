/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author emazi
 */
public class Computer extends Thread{
    
    public double probabilityToHit = 0;
    private boolean probability;
    private Battleship manager;
    private int y;
    private int x;
    private ArrayList<Integer> position = new ArrayList<Integer>();
    private ArrayList<Integer> randomArray = new ArrayList<Integer>();
    private Player player = new Player();
    public boolean attack = false;
    private int numberOfShips = 20;
    
    //Konstruktor
    Computer(Player player){
        this.player = player;
        addToArray();
    }
    
    @Override
    public void run(){
        while(numberOfShips > 0){
          synchronize();
            if(attack){
                try {
                    tryToHit(player);
                    TimeUnit.MILLISECONDS.sleep(500);
                    destroyShip(y, x);
               
               } catch (InterruptedException ex) {
                   Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        
       
    }
    public synchronized void setAttack(boolean attack){
      this.attack = attack;
    }
    public synchronized void synchronize(){
      
    }
    private void checkNumberOfShips(){
        Platform.setImplicitExit(false);
        if(--numberOfShips == 0){ // If 0 = Computer has won
            GameVScomputerController.winMessage(2);
        }
    } 
    
    private void attack(Player player, boolean promission){
        int rnd;
        rnd = randomFunc(position.size() - 1);
        y = position.get(rnd);
        x = position.get(rnd+1);
    }
    
    private void destroyShip(int y, int x){
        if(player.getMap().map[y][x].isShip()){
            randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y)+Integer.toString(x))));
            player.getMap().map[y][x].setHitted(true);
            //setNeighbour(true,y, x, player);
            findDestroyedShip();
            checkNumberOfShips();
            tryToHit(player); 
        }else{
            randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y)+Integer.toString(x))));
            player.getMap().map[y][x].setChecked(true);
            attack = false;
            
            //System.out.println("check this Ship Y = "+y+" X = "+x);
        }
  
        
    }
    
    public void tryToHit(Player player){
            int random;
            probability = Math.random() < (probabilityToHit/100);
            // System.out.println("probablitiy = "+ probability);
            position = findNotDestroyedShip(player,probability);
            // System.out.println("position is " + position);
            System.out.println("IS postion empty = "+position);
            if(position.isEmpty()){
                random = random(player, probability);
                findShip(player, random);
            }else{
                attack(player,probability);    
            }
       
          
        
    }
    
    private ArrayList<Integer> findNotDestroyedShip(Player player, boolean promission){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    if(xAxis.isShip()){
                        if(xAxis.isChecked() && !xAxis.getHitted()){
                            continue;
                        }else if(xAxis.getHitted() && !xAxis.isDestroyed()){
                            y = xAxis.getY();
                            x = xAxis.getX();
                            if(findNeigh(player,y,x,true).isEmpty() && findNeigh(player,y,x,false).isEmpty()){
                                continue;
                            }
                            //System.out.println("found not Destroyed Y ="+y+" X = "+x);
                            if(findNeigh(player,y,x,false).isEmpty()){
                                return findNeigh(player,y,x,true);
                            }else if(findNeigh(player,y,x,promission).isEmpty()){
                                continue;
                            }
                            return findNeigh(player,y,x,promission);
                        }  
                    }
         }      }
         return new ArrayList<>();
    }
   private boolean findShip(Player player, int shipID){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    //System.out.println("xAxis.getIDShip() = "+xAxis.getShipID() +" shipID = "+ shipID);
                    if(xAxis.getShipID() == shipID){
                        System.out.println("");
                       //destroyShip( xAxis.getY(), xAxis.getX());
                       y = xAxis.getY();
                       x = xAxis.getX();
                       if(xAxis.isShip()){
                        return true;
                       }else{
                         return false;
                       }
                    }
         }      }
        System.out.println("Error: ID not find");
        return false;
    }
    
    
     private ArrayList<Integer> findNeigh(Player player, int y, int x, boolean promission){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int [] yx = {y,x};
        int [] add = {0,1,0};
        
        for(int a = 0; a < 2; a++){
            if(yx[a] +1 < player.getMap().map.length){
                if(!player.getMap().map[y+add[a+1]][x+add[a]].isChecked() && player.getMap().map[y+add[a+1]][x+add[a]].isShip() == promission && !player.getMap().map[y+add[a+1]][x+add[a]].getIsNeighbour()){
                    position.add(y+add[a+1]);
                    position.add(x+add[a]);
                }    
            }
            if(yx[a] - 1 >= 0){
                
                if(!player.getMap().map[y-add[a+1]][x-add[a]].isChecked()  && player.getMap().map[y-add[a+1]][x-add[a]].isShip() == promission && !player.getMap().map[y-add[a+1]][x-add[a]].getIsNeighbour()){
                    position.add(y-add[a+1]);
                    position.add(x-add[a]);
                }
            }
        }
        return position;
    }   
    

   public int randomFunc(int size)
    {
        ArrayList<Integer> list = new ArrayList();
        for(int index = 0; index < size; index ++){
            if(index % 2 == 0){
                list.add(index);
            }
        }
        if(list.size()-1 == 0){
          return 0;
        }
        else{
          return list.get(new Random().nextInt(list.size()-1));  
        }
        
    }
    public int random(Player player, boolean promission){
        int randomNumber,y,x;
        ArrayList<Integer> list = new ArrayList();
        list = randomArray;
         int position = 0;
         System.out.println("SIZE = "+list.size());
         System.out.println("Promission = "+promission);
        for(int index = list.size(); index > 0; index --){
            randomNumber = new Random().nextInt(index);
            y = getY(list.get(randomNumber));
            x = getX(list.get(randomNumber));
            System.out.println("RandomNumber = "+randomNumber);
            
                if(player.getMap().map[y][x].isShip() && promission){
                    position = randomArray.get(new Integer(randomNumber));
                    System.out.println("Position = "+position);
                     return position;
                }else if(!player.getMap().map[y][x].isShip() && !promission && !player.getMap().map[y][x].getIsNeighbour()){
                    position = randomArray.get(new Integer(randomNumber));;
                    System.out.println("Position = "+position);
                    return position;
                }
        //maybe cannot find
        }
        System.out.println("error in Function \"Random\"");
        return 0;
    }
    private int getY(int yx){
        String str;
        if(yx < 10){
          str = "0";
        }else{
            str = Integer.toString(yx).substring(0,1);
        }
        int y = Integer.parseInt(str);
        return y;  
    }
    private int getX(int yx){
        String str = Integer.toString(yx);
        if(str.length() == 1 && str != "0"){
            str = "0" +  Integer.toString(yx).substring(0,1);            
        }else if(str == "0"){
            str = "0";
        }else{
            str = Integer.toString(yx).substring(1,2);
        }
        int y = Integer.parseInt(str);
        return y;  
    }
    
    
    private void addToArray(){
        for(int index = 0; index < 100; index++){
            randomArray.add(index);
        }
    }   
   
         /* SET Neighbour */
    //  Setzt Nachbarn ein 
     public void findDestroyedShip(){
        String yx = "";
        int y;
        int x;
        String ok;

        //Ship 1
        for(int index = 0; index <= 3; index++){                 
            for(ArrayList a : player.getShipsPosition2().get(index)){
                yx = a.toString();
                yx = yx.replaceAll("[^-?0-9]+", ""); 
                if(index == 0 && yx.length() == 2 ){
                  ok =  setDestroyedField(yx);
                }
                if(index == 1 && yx.length() == 4){
                  ok = setDestroyedField(yx);
                }
                if(index == 2 && yx.length() == 6){
                  ok = setDestroyedField(yx);
                }
                if(index == 3 && yx.length() == 8){
                  ok =  setDestroyedField(yx);
                }
            }
        }
    }  
    
    public String setDestroyedField(String yx){
        int y,x;
        int size = yx.length();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int zero = -1;
        int one = 0;
        int count = 0;
        int number = 0;
         int index1 = 0;
   
        for(int index =0; index < size; index ++){
            zero ++;
            one ++; 
            list.add(Integer.parseInt(yx.substring(zero,one))); 
        }
        number = 0;
        for(int index =1; index <= size/2; index ++){
            if(player.getMap().map[list.get(number)][list.get(number+1)].getHitted()){
                count ++;  
            }
          number = number+2;
          index1 = index;
        }
    
        if(count == index1){
            number = 0;
            for(int index =0; index < size/2; index ++){  
              player.getMap().map[list.get(number)][list.get(number+1)].setDestroyed(true);  
              setNeighbour(true,list.get(number),list.get(number+1));
              number = number+2;
            }
           return "Destroyed";  
        }
        return "Not Destroyed";
    }
    
        public void setNeighbour(boolean setNeigh,int y, int x){
         if(y - 1 >= 0){//Falls Feld nich ganz oben ist
             player.getMap().map[y - 1][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
             randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y-1)+Integer.toString(x))));
                if(x + 1 < player.getMap().map.length){//Falls Feld nicht ganz oben rechts ist
                   player.getMap().map[y-1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                   randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y-1)+Integer.toString(x+1))));
                }
                if(x - 1 >= 0){//Falls Feld nicht ganz oben links ist
                   player.getMap().map[y-1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                   randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y-1)+Integer.toString(x-1))));
                }
         }
         if(y + 1<player.getMap().map.length){ ///Falls Feld nicht ganz unten ist
            player.getMap().map[y+1][x].setIsNeighbour(setNeigh);
            randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y+1)+Integer.toString(x))));
                if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts unten ist
                   player.getMap().map[y+1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                   randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y+1)+Integer.toString(x+1))));
                }
                if(x - 1 >= 0){///Falls Feld nicht ganz links unten ist
                    player.getMap().map[y+1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                    randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y+1)+Integer.toString(x-1))));
                }
        }
        if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts ist
             player.getMap().map[y][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
             randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y)+Integer.toString(x+1))));
         }
        if(x - 1 >= 0){///Falls Feld links ganz unten ist
             player.getMap().map[y][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
             randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y)+Integer.toString(x-1))));
         }
        player.getMap().map[y][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
        randomArray.remove(new Integer(Integer.parseInt(Integer.toString(y)+Integer.toString(x))));
    }
     


}
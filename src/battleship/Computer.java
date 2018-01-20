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

/**
 *
 * @author emazi
 */
public class Computer extends Thread{
    
    public double probabilityToHit = 0;
    private boolean probability;
    private int y;
    private int x;
    private ArrayList<Integer> position = new ArrayList<Integer>();
    private ArrayList<Integer> randomArray = new ArrayList<Integer>();
    private Player player = new Player();
    public boolean attack = false;
    public boolean running = true;
    
    //Konstruktor
    Computer(Player player){
        this.player = player;
        addToArray();
    }
    
    @Override
    public void run(){
        while(running){
            System.out.println("hey0");
            if(attack){
                System.out.println("hey");
                try {
                    tryToHit(player);
                    TimeUnit.SECONDS.sleep(1);
                    destroyShip(y, x);
               
                } catch (InterruptedException ex) {
                    Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
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
            player.findDestroyedShip();
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
                            if(findNeigh(player,y,x,promission).isEmpty()){
                                return findNeigh(player,y,x,true);
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
         System.out.println("SIZE IS "+list.size());
         
        for(int index = list.size(); index > 0; index --){
            randomNumber = new Random().nextInt(index);
            y = getY(list.get(randomNumber));
            x = getX(list.get(randomNumber));
            System.out.println("RANDOM = "+promission+" YX = "+list.get(randomNumber));
            
                if(player.getMap().map[y][x].isShip() && promission){
                    position = randomArray.get(randomNumber);
                    System.out.println("Position = "+position);
                     return position;
                }else if(!player.getMap().map[y][x].isShip() && !promission && !player.getMap().map[y][x].getIsNeighbour()){
                    position = randomArray.get(randomNumber);;
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
    public void setNeighbour(boolean setNeigh,int y, int x, Player player){
         if(y - 1 >= 0){//Falls Feld nich ganz oben ist
             player.getMap().map[y - 1][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                if(x + 1 < player.getMap().map.length){//Falls Feld nicht ganz oben rechts ist
                    player.getMap().map[y-1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){//Falls Feld nicht ganz oben links ist
                    player.getMap().map[y-1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
         }
         if(y + 1< player.getMap().map.length){ ///Falls Feld nicht ganz unten ist
            player.getMap().map[y+1][x].setIsNeighbour(setNeigh);
                if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts unten ist
                    player.getMap().map[y+1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
                if(x - 1 >= 0){///Falls Feld nicht ganz links unten ist
                    player.getMap().map[y+1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
                }
        }
        if(x + 1 < player.getMap().map.length){///Falls Feld nicht ganz rechts ist
             player.getMap().map[y][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        if(x - 1 >= 0){///Falls Feld links ganz unten ist
             player.getMap().map[y][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
         }
        player.getMap().map[y][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
    }
     


}
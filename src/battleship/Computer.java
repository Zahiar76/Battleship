/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emazi
 */
public class Computer {
    
    public double probabilityToHit = 0;
    private boolean probability;
    private int y;
    private int x;
    private ArrayList<Integer> position = new ArrayList<Integer>();
    
    private void attack(Player player){
        findNotDestroyedShip(player);
        int rnd;

            //Need to be change, if ships will be bigger
            switch (position.size()) {
                case 2:
                    break;
            //Computer po trafieniu znow ma tylko 50% szans na trafienie, 
            // jesli statek zostanie trafiony, bedzie probowal dalej trafiac dalej, (powinno byc ze proboje trafiac kolo statku narazie ma 100%)
            //Trzeba tez obstawic zatopiony statek zeby komputer nie szczelal obok zatopionego statku
            //Jak to bd zrobione mozna zaczac programowac ruch komputer 
                case 4:
                      rnd =  randomFunc();
                      destroyShip(player,position.get(rnd),position.get(rnd+1));
                      tryToHit(player);
                      System.out.println("destoryed Func1");
                      break;
                case 6:
                      rnd = randomFunc2();
                      destroyShip(player,position.get(rnd),position.get(rnd+1));
                      tryToHit(player);
                      System.out.println("destoryed Func2");
                    break;
                default:
                    break;
            }
 
    }
    
    private void destroyShip(Player player, int y, int x){
        player.getMap().map[y][x].setHitted(true);
        player.findDestroyedShip();
        tryToHit(player);//again
        System.out.println("again");
        
    }
    
    public void tryToHit(Player player){
        boolean probability;
        probability = Math.random() < (probabilityToHit/100);
        System.out.println("probablitiy = "+ probabilityToHit/100);
        if(probability){
          System.out.println("ATTACK");
          findNotDestroyedShip(player);
          if(position.isEmpty()){
             int random = GameVScomputerController.random();
             if(findShip(player, random)){
                tryToHit(player); 
             };
             GameVScomputerController.random.remove(random);
             
          }
            
          
        }
    }
    
    private void findNotDestroyedShip(Player player){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    if(xAxis.isShip()){
                        if(xAxis.isChecked() && !xAxis.getHitted()){
                            continue;
                        }else if(xAxis.getHitted()){
                            y = xAxis.getY();
                            x = xAxis.getX();
                            position = findNeigh(player,y,x);
                             System.out.println("found not Destroyed Y ="+y+" X = "+x);
                        }  
                    }
         }      }
    }
   private boolean findShip(Player player, int shipID){
         for(Field[] yAxis: player.getMap().map){
                for(Field xAxis : yAxis){
                    System.out.println("xAxis.getIDShip() = "+xAxis.getShipID() +" shipID = "+ shipID);
                    if(xAxis.getShipID() == shipID && xAxis.isShip()){
                       destroyShip(player, xAxis.getY(), xAxis.getX()); 
                       return true;
                    }else if(xAxis.getShipID() == shipID && !xAxis.isShip()){
                       player.getMap().map[xAxis.getY()][xAxis.getX()].setChecked(true);
                       return false;
                    }
         }      }
        System.out.println("Error: ID not find");
        return false;
    }
    
    
     private ArrayList<Integer> findNeigh(Player player, int y, int x){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int [] yx = {y,x};
        int [] add = {0,1,0};
        
        for(int a = 0; a < 2; a++){
            if(yx[a] +1 < player.getMap().map.length){
                if(player.getMap().map[y+add[a+1]][x+add[a]].isShip() && !player.getMap().map[y+add[a+1]][x+add[a]].getHitted()){
                    position.add(y+add[a+1]);
                    position.add(x+add[a]);
                }    
            }  
        }
        for(int a = 0; a < 2; a++){
            if(yx[a] - 1 >= 0){
                if(player.getMap().map[y-add[a+1]][x-add[a]].isShip() && !player.getMap().map[y-add[a+1]][x-add[a]].getHitted()){
                    position.add(y-add[a+1]);
                    position.add(x-add[a]);
                }  
            }
        }
        System.out.println(position);
        return position;
    }   
    
    public int randomFunc()
    {
        return Math.random() > 0.5 ? 0 : 2;
    }
   public int randomFunc2()
    {
        int [] list = {0,2,4};
        int random;
        return list[new Random().nextInt(3)];
    }
     
     


}
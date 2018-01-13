
import battleship.Field;
import java.util.ArrayList;
import javafx.fxml.FXML;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Emil Poorando
 */
public class test {
    
//       //set Nachbarn
//   @FXML
//   public void setNeighbour(String lokalisation, boolean neigh){
//    int whichShip = 0;
//        for(ArrayList typ : shipsPosition2){
//        int number = 0;
//        for(Object a : typ){
//             String str = a.toString();
//                if(str.indexOf(lokalisation) >= 0){
//                  //  System.out.println(shipsPosition2.get(whichShip).get(number).get(0));
//                   for(String n : shipsPosition2.get(whichShip).get(number)){
//                       String y = n.substring(0,1); String x = n.substring(1,2);
//                      // map.setNeighbour(neigh,Integer.parseInt(y), Integer.parseInt(x));
//                       //break;
//                   } 
//                   if(neigh == false && (shipsPosition2.get(whichShip).get(number).size() == 0 || shipsPosition2.get(whichShip).get(number).size() == whichShip+1)){
//                       map.index[whichShip] --;
//                      // showQuantityOfShips(whichShip);
//                   }
//                break;
//            }
//            number ++;
//        }
//     whichShip ++;
//  
//    }
//     
//   }
    /* SET Neighbour */
      //Setzt Nachbarn ein 
//    public void setNeighbour(boolean setNeigh,int y, int x){
//         if(y - 1 >= 0){//Falls Feld nich ganz oben ist
//             map[y - 1][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//                if(x + 1 < map.length){//Falls Feld nicht ganz oben rechts ist
//                    map[y-1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//                }
//                if(x - 1 >= 0){//Falls Feld nicht ganz oben links ist
//                    map[y-1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//                }
//         }
//         if(y + 1< map.length){ ///Falls Feld nicht ganz unten ist
//            map[y+1][x].setIsNeighbour(setNeigh);
//                if(x + 1 < map.length){///Falls Feld nicht ganz rechts unten ist
//                    map[y+1][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//                }
//                if(x - 1 >= 0){///Falls Feld nicht ganz links unten ist
//                    map[y+1][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//                }
//        }
//        if(x + 1 < map.length){///Falls Feld nicht ganz rechts ist
//             map[y][x+1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//         }
//        if(x - 1 >= 0){///Falls Feld links ganz unten ist
//             map[y][x-1].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//         }
//        map[y][x].setIsNeighbour(setNeigh);//Feld als Nachbarn markiert
//    }

    
    /* OLD genungPlatz - Methode */
//      public boolean enoughPlaceForShip(int y, int x,int whichShip, Field[][] map1){
//        Field[][] map = new Field[10][10];
//        
//        for(Field[] g : map1){
//            for(Field j : g){
//                map[j.getY()][j.getX()] = new Field();
//                if(j.isShip()){
//                    map[j.getY()][j.getX()].setShip(true);
//                }
//                if(j.getIsNeighbour()){
//                    map[j.getY()][j.getX()].setIsNeighbour(true);
//                }
//                
//                map[j.getY()][j.getX()].getBtn().setId(Integer.toString(j.getY())+""+Integer.toString(j.getX())); // Setzt die ID des Buttons ein
//            }  
//        }
//        
//        insertFieldToArray(map);
//        
//        int random,indexOfArray = 100;
//        String a = Integer.toString(y);
//        a += Integer.toString(x);
//        list.get(Integer.parseInt(a)).setShip(true);
//        int ok = 0;
//        
//        for(boolean isOk = false; isOk == false;){ 
//            //System.out.println(ok);
//             random = (int) (Math.random() * indexOfArray);
//               // System.out.println(random);
//             if(!list.get(random).getIsNeighbour() && !list.get(random).isShip()){
//                y = list.get(random).getY();
//                x = list.get(random).getX();
//               // System.out.println(indexOfArray);
//                if(y - 1 >= 0){
//                   if(map[y-1][x].isShip()&& !map[y-1][x].getIsNeighbour()){
//                       ok++;
//                       list.get(random).setShip(true);
//                       map[y][x].setShip(true);
//                      // System.out.println(y+"=Y"+x+"=X");
//                       continue;} 
//                }
//                if(x+ 1 < map.length){
//                   if(map[y][x+1].isShip() && !map[y][x+1].getIsNeighbour()){
//                       ok++;
//                       list.get(random).setShip(true);
//                       map[y][x].setShip(true);
//                       //System.out.println(y+"=Y"+x+"=X");
//                       continue;}
//                }
//                if(y+ 1 < map.length){
//                   if(map[y+1][x].isShip() && !map[y+1][x].getIsNeighbour()){
//                       ok++;
//                       list.get(random).setShip(true);
//                       map[y][x].setShip(true);
//                      // System.out.println(y+"=Y"+x+"=X");
//                       continue;}
//                }
//                if(x-1>= 0){
//                    if(map[y][x-1].isShip() && !map[y][x-1].getIsNeighbour()){
//                       ok++;
//                       list.get(random).setShip(true);
//                       map[y][x].setShip(true);
//                   //    System.out.println(y+"=Y"+x+"=X");
//                       continue;}   
//                       
//                       
//                }
//            }
//             if(indexOfArray > 0){
//                  list.remove(random);
//                   // System.out.println("remove");
//                  indexOfArray --;
//             }
//             if(indexOfArray <= 0){
//                  //  System.out.println("end");
//                    return false;
//            }
//             if(ok == whichShip-1){
//                   System.out.println("U CAN DO THIS");
//                    return true;
//                    
//                }
//        }
//       // System.out.println("not good");
//        return false;
//     } 
    
    
    /*Set Computer Map */
//        public void createMapForComputer(){
//        boolean shipCanBeSetted = false; //Sucht einen Freien Platz
//        int y,x,random,indexOfArray = 100; //Achse Y und X
//        y = 0;
//        x = 0;
//        ArrayList<Field> list = new ArrayList<Field>();
//
//   
//        //Wie Viel ?
//        for(int anzahl = allShipsComputer.get(0)[0].length; anzahl > 0; anzahl --){
//            //Freier Platz
//            shipCanBeSetted = false; 
//            System.out.println("Anzahl is"+anzahl);
//            while(!shipCanBeSetted){
//                y = (int) (Math.random() * 10);
//                x = (int) (Math.random() * 10);
//                if(!map[y][x].getIsNeighbour() && !map[y][x].isShip()){
//                  shipCanBeSetted = true; 
//                  map[y][x].setShip(true); 
//                  list = insertFieldToArray();
//                  computer.getShipsPosition().get(0).add(y);//Position des Schiffes wird gespeichert
//                  computer.getShipsPosition().get(0).add(x);//Position des Schiffes wird gespiechert
//                    //System.out.println("d");
//                }     
//            }
//
//            //Wie Gross?
//            for(int groesse = 1; groesse < allShipsComputer.get(0).length -1; groesse ++){
//            shipCanBeSetted = false;
//            while(shipCanBeSetted != true){
//                System.out.println("ok");
//                random = (int) (Math.random() * indexOfArray);
//                
//                if(!list.get(random).getIsNeighbour() && !list.get(random).isShip()){
//                   y = list.get(random).getY();
//                   x = list.get(random).getX();
//                   if(y - 1 >= 0){
//                      if(map[y-1][x].isShip()){
//                          shipCanBeSetted = true;}
//                   }
//                   if(x+ 1 < map.length){
//                      if(map[y][x+1].isShip()){
//                          shipCanBeSetted = true;}                 
//                   }
//                   if(y+ 1 < map.length){
//                      if(map[y+1][x].isShip()){
//                          shipCanBeSetted = true;}                 
//                   }
//                   if(x-1>= 0){
//                       if(map[y][x-1].isShip()){
//                          shipCanBeSetted = true;}                
//                   }
//                   if(shipCanBeSetted == true){
//                    list.get(random).setShip(true);
//                    map[y][x].setShip(true);
//                                      computer.getShipsPosition().get(0).add(y);//Position des Schiffes wird gespeichert
//                  computer.getShipsPosition().get(0).add(x);//Position des Schiffes wird gespiechert
//                   }
//                   
//                }else{
//                     list.remove(random);
//                     indexOfArray --;
//                   }
//            }
//        
//         }   
//                
////            if(!map[y][x].getIsNeighbour() && !map[y][x].isShip()){
////                if(y-1 < 0){
////                    if(map[y-1][x].isShip()){
////                      map[y][x].setShip(true); 
////                    }
////                }
////            }
////                  shipCanBeSetted = true; 
////                  map[y][x].setShip(true); 
////                  System.out.println(y+" und "+x);
//                    //System.out.println("d");
//            
////                System.out.println("random is "+random +" and random2 is " + random2);
////                done = false;
////                
////                for(int b = 0; b < groesse;b++){
////                    if(howManyBoxes[b] == null){
////                        howManyBoxes[b] =  savePossibleBoxes(y,x,b);}
////                        System.out.println("Box checked and its" + howManyBoxes[b]);
////                    if(b == groesse && howManyBoxes == null){
////                        System.out.println("kein platz");
////                        computer.getShipsPosition().get(whichShip-1).clear();    
////                        createMapForComputer();
////                        break;
////
////                    }
////                }
////            
////                while(done != true){
////                    //Falsche Y UND X weil sie kommen nicht vom Possible Ships
////                    if(howManyBoxes[0] != null && random == 3){
////                        if(!howManyBoxes[0].equals("")){
////                        done = setBox(howManyBoxes,random, random2,0);System.out.println("zero");}
////                        
////                    }
////                    if(howManyBoxes[1] != null && random == 2){
////                        if(!howManyBoxes[1].equals("")){
////                        done = setBox(howManyBoxes,random, random2,1);System.out.println("one");}
////                    }
////                    if(howManyBoxes[2] != null && random == 1){
////                        if(!howManyBoxes[2].equals("")){
////                        done = setBox(howManyBoxes,random, random2,2);System.out.println("two");}
////                    }
////                    if(howManyBoxes[3] != null && random == 0){
////                        if(!howManyBoxes[3].equals("")){
////                        done = setBox(howManyBoxes,random, random2,3);System.out.println("three");}
////                    }
////                         if(random >= 3){
////                            random = 0;
////                         
////                        }else{
////                               random++;
////                           }
////                      
////                    if(whichBox == 3 && done == false){
////                        computer.getShipsPosition().get(whichShip-1).clear();  
////                        System.out.println("ok");
////                        createMapForComputer();
////                        break;
////                }    }
////                  
////                }
////            }
//        } 
//    }
//    //Fügt Felder in eine ArrayListe hinzu
//    public ArrayList insertFieldToArray(){
//        ArrayList<Field> list = new ArrayList<Field>();
//        for(Field[] y : map){
//            for(Field x : y){
//                list.add(x);
//                //System.out.println(x);
//            }  
//        }
//        return list;
//    }
//    
//    //Schiffe falsch gespeiechert weil ich nachher nicht weiss, wo sie sich befinden
//    public String savePossibleBoxes(int y, int x,int whichBox){
//        String howMuchNeighbour = "";
//        if(y - 1 >= 0 && !map[y - 1][x].isShip() && !map[y - 1][x].getIsNeighbour()){
//          howMuchNeighbour += "1";}
//          possibleShips.get(whichBox)[0][0] = y-1;
//          possibleShips.get(whichBox)[0][1] = x;
//        if(x + 1 < map.length && !map[y][x+1].isShip() && !map[y][x+1].getIsNeighbour()){
//          howMuchNeighbour += "2";}  
//          possibleShips.get(whichBox)[1][0] = y;
//          possibleShips.get(whichBox)[1][1] = x+1;
//        if(y + 1< map.length && !map[y + 1][x].isShip() && !map[y + 1][x].getIsNeighbour()){
//          howMuchNeighbour += "3";}
//          possibleShips.get(whichBox)[2][0] = y+1;
//          possibleShips.get(whichBox)[2][1] = x;
//        if(x - 1 >= 0 && !map[y][x-1].isShip() && !map[y][x-1].getIsNeighbour()){
//          howMuchNeighbour += "4";
//          possibleShips.get(whichBox)[3][0] = y;
//          possibleShips.get(whichBox)[3][1] = x-1;
//        }
//        return howMuchNeighbour;
//    }
//    public boolean setComputerShip(int y, int x){
//        if(!map[y][x].getIsNeighbour() && !map[y][x].isShip()){
//           map[y][x].setShip(true); 
//            System.out.println(y+" und "+x);
//           return true;
//        }return false;  
//    }
//    
//    public boolean setBox(String[] howManyBoxes,int random, int random2,int whichBox){
//        boolean done = false;
//        while(done != true){
//        if(howManyBoxes[whichBox].contains("1") && random2 ==3){
//            done = setComputerShip(possibleShips.get(whichBox)[0][0],possibleShips.get(whichBox)[0][1]);
//            System.out.println("here 1" + done);
//       }else if(howManyBoxes[whichBox].contains("2") && random2 ==2){
//           done = setComputerShip(possibleShips.get(whichBox)[1][0],possibleShips.get(whichBox)[1][1]);
//           System.out.println("here 2" + done);
//       }else if(howManyBoxes[whichBox].contains("3") && random2 ==1){
//           done = setComputerShip(possibleShips.get(whichBox)[2][0],possibleShips.get(whichBox)[2][1]);
//           System.out.println("here 3" + done);
//       }else if(howManyBoxes[whichBox].contains("4") && random2 ==0){
//           done = setComputerShip(possibleShips.get(whichBox)[3][0],possibleShips.get(whichBox)[3][1]);
//           System.out.println("here 4" + done);
//       }
//
//          if(random2 >= 3){
//              random2 = 0;
//
//          }else{
//              random2++;
//          }
//      
//     }
//      return done;
//    }
}

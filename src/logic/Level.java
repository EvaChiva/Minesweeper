/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models a level
 */
public class Level implements java.io.Serializable {
    
    //Attributtes
    private int rows;
    private int columns;
    private int bombs;
    private int level;
    
    //Constructor
    public Level (int level){
        //Sets values of attributtes according to the given level
        if (level == 1) {
            //Hard level
            rows = 10;
            columns = 10;
            bombs = 20;
        } else {
            //Easy level
            rows = 7; 
            columns = 7;
            bombs = 10;
        }
        this.level = level;
        
    }
    
    //Gets number of rows in the level
    public int getRows(){
        return rows;
    }
    
    //Gets number of columns in the level
    public int getColumns(){
        return columns;
    }
    
    //Gets number of bombs in the level
    public int getBombs(){
        return bombs;
    }
    
    //Gets the number of the level
    public int getLevel() {
        return this.level;
    }
}

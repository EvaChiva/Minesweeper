/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.ArrayList;
/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models a grid of squares
 */
public class Grid implements java.io.Serializable{
    //Attributtes
    private Square[][] gridSquares;
    private Level level;
    
    public Grid(int level){
        //Creates
        this.level = new Level(level);
        gridSquares = new Square [this.level.getColumns()][this.level.getRows()];
        
        //Bombs
        int bombCounter = 0;
        //Goes through matrix for placing bombs
        int i,j;
        for(i = 0; i < this.level.getColumns(); i++){
            //If the number of bombs reaches the limit, the loop will stop
            if(bombCounter >= this.level.getBombs()){
                break;
            }
            for(j = 0; j < this.level.getRows(); j++){
              
		//Gives a random number between 0 and 4 because every bomb has a 1/5 probability of appearing
		int intAleatorio = (int)(Math.random()*4);
                if(intAleatorio == 0){
                    this.gridSquares[i][j] = new Bomb();
                    bombCounter++;
                    if(bombCounter >= this.level.getBombs()){
                         //If the number of bombs reaches the limit, the loop will stop
                        break;
                    }
                }
   
            }         
        }
        
        int x, y;
        x = 0;
        y = 0;
        int i0 = 0;
        int j0 = 0;
         //Goes through matrix for placing numbers
        for(i = 0; i < this.level.getColumns(); i++){
            for(j = 0; j < this.level.getRows(); j++){
                bombCounter = 0;
                if(this.gridSquares[i][j] instanceof Bomb){
                } else {
                    //Will consider to place a number if there is not a bomb
                    //Initial conditions for loop
                    x = i - 1;
                    y = j - 1;
                    i0 = i;
                    j0 = j;
                    if (j == 0) {
                        //Upper row
                        y = j;
                    } if (j == (this.level.getColumns()-1)) {
                        //Lowe row
                        j0 = j - 1;

                    } if ( i == (this.level.getRows()-1)) {
                        //Right column
                        i0 = i - 1;

                    } if ( i == 0) {
                        //left column
                        x = i;
                    } 
                  
                    //Goes around the number to check the amount of bombs there are
                    for (; x <= (i0+1); x++){
                        y = j - 1;
                        if (j == 0) {
                            //Upper row
                            y = j;
                        
                        } 
                        for(; y <= (j0+1); y++){          
                            if(this.gridSquares[x][y] instanceof Bomb) {
                                   bombCounter++;
                            }
                            
                        }
                    }
                }
                
                if(bombCounter != 0){
                    //Creates number with the number of bombs around it
                    this.gridSquares[i][j] = new Number(bombCounter);
                } 
                 
            }
        }
        
        //Arrays for placing positions around whites
        ArrayList<Integer> yPos = new ArrayList<Integer>();
        ArrayList<Integer> xPos = new ArrayList<Integer>();
       
        x = 0;
        y = 0;
        //Goes through matrix for placing white squares
        for(i = 0; i < this.level.getColumns(); i++){
            for(j = 0; j < this.level.getRows(); j++){
                //Clean arrays for new square
                xPos.clear();
                yPos.clear();
                if(this.gridSquares[i][j] instanceof Bomb || this.gridSquares[i][j] instanceof Number){
                } else {
                    //Sets initial conditions for loop
                    x = i - 1;
                    y = j - 1;
                    i0 = i;
                    j0 = j;
                    if (j == 0) {
                        //Upper row
                        y = j;
                        //j0 = 0;
                    } if (j == (this.level.getColumns()-1)) {
                       //lower row
                        j0 = j - 1;

                    } if ( i == (this.level.getRows()-1)) {
                        //Right column
                        i0 = i - 1;

                    } if ( i == 0) {
                        //Left column
                        x = i;
                    }
                   
                    //Goes around the number to check if those positions are white squares or numbers (not bombs)
                    for (; x <= (i0+1); x++){
                        y = j - 1;
                        if (j == 0) {
                            //Lower row
                            y = j;          
                        } 
                        for(; y <= (j0+1); y++){
                            
                            if(!(this.gridSquares[x][y] instanceof Bomb)) {
                                   xPos.add(x);
                                   yPos.add(y);
                               }
                        }
                    }
                    int[] yCoor = new int[yPos.size()];
                    int[] xCoor = new int[xPos.size()];
                    
                    //Turns array lists into normal arrays
                    for (int k=0; k < yCoor.length; k++)
                    {
                        yCoor[k] = (yPos.get(k)).intValue();
                        xCoor[k] = (xPos.get(k)).intValue();
                    }
                    //Creates white square with corresponding positions
                    this.gridSquares[i][j] = new WhiteSquare(xCoor, yCoor);
                }
            }
        }  
    }

    /**
     * getSquare. returns a given square
     * @param x. Position x
     * @param y. Position Y
     * @return square
     */
    public Square getSquare(int x, int y){
        return gridSquares[x][y];
    }
    
    /**
     * getLevel. get level that is used for the grid currently used
     * @return level
     */
    public Level getLevel (){
        return level;
    }
    
    /**
     * lookForBombs. Looks for the bombs in the array
     * @return Array with x and y positions
     */
    public int[] lookForBombs() {
        //Initializes array
        int[] bombPos = new int[(level.getBombs())*2];
        int i, j;
        int counter = 0;
        //Gies through matrix looking for bombs
        for(i = 0; i <  level.getColumns() ; i++) {
            for(j = 0; j <level.getRows() ; j++) {
                if(gridSquares[i][j] instanceof Bomb) {
                    //Sets positions for saving in the array in the corresponding index (given by counter)
                    bombPos[counter] = i;
                    bombPos[counter+1] = j;
                    //Need to grow by 2 because every time 2 items are saved in the array
                    counter = counter + 2;    
                }
            }
        }
        //Returns positions of the bombs
        return bombPos;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models the basic features of the game
 */
public class Game {
     /**
      * win. This methos checks if the user has already won
      * @param currentGrid
      * @return if the user already won or not
      */
    public boolean win(Grid currentGrid){
        int clickedSquares = 0;
        //Goes through matrix looking for the already clicked squares 
        for(int i = 0; i <currentGrid.getLevel().getColumns(); i++) {
            for(int j = 0; j <currentGrid.getLevel().getRows(); j++) {
                if(currentGrid.getSquare(i, j).getFlag()) {
                   //Counter increases if the square has already been clicked 
                    clickedSquares++;                  
                }
            }
        }
        
        if (clickedSquares == ((currentGrid.getLevel().getColumns())*(currentGrid.getLevel().getRows())- currentGrid.getLevel().getBombs())){
           //Checks if the clicked squares are the amount of squares that are not bombs
            return true;
        } else {
            return false;
        }
    } 
    
    /**
     * newGame. This method returns a new grid
     * @param currentGrid. The grid that is being used
     * @param level. asks for the level the user is playing
     * @return currentGrid. new grid for the game
     */
    public Grid newGame (Grid currentGrid, int level) {
        //Cleans object
        currentGrid = null;
        //Sets a new one and returns it
        currentGrid = new Grid(level);
        return currentGrid;
    }
    
    
}

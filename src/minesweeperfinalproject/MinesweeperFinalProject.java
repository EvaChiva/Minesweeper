/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperfinalproject;
import logic.*;
/**
 *
 * @author evabeltran
 */
public class MinesweeperFinalProject {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Grid grid = new Grid(1);
        int i, j;
        for(i = 0; i < 10; i++){
            for(j = 0; j < 10; j++){
                if (grid.getSquare(i, j) instanceof Bomb) {
                    System.out.print(9);
                } else if (grid.getSquare(i, j) instanceof logic.Number){ 
                    System.out.print(((logic.Number)(grid.getSquare(i, j))).getNumber());
                } else {
                    System.out.print("0");
                }
                
                
            }
            System.out.println();
        }
    }
    
}

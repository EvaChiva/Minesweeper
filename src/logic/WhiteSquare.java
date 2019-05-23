/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models a white square
 */
public class WhiteSquare extends Square{
    
    //Attributtes that save the coordinates are around the white square
    private int[] yCoordinates;
    private int[] xCoordinates;
    
    //Constructor, asks for the coordinates
    public WhiteSquare (int[] x, int[]y) {
        yCoordinates = y;
        xCoordinates = x;
    }
    
    //Returns the y coordinates
    public int[] getY(){
        return yCoordinates;
    }
    
    //Returns the x coordinates
    public int[] getX(){
        return xCoordinates;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.*;


/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models the hard level of the minesweeper with a GUI approach
 */
public class fileSerialiazer {
    public void serializeGrid(String name, Grid currentGrid) {
         try {
            //Opens or creates file
            FileOutputStream fileOut = new FileOutputStream(name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            //Serializes the objects
            out.writeObject(currentGrid);
            
            //Closes files
            out.close();
            fileOut.close();
            
      	} catch (IOException e) {
            e.printStackTrace();
            
      	} 
    }
    
    
}

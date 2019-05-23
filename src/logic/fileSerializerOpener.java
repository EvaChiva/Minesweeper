/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.*;


/**
 * @date: 11/may/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class is for opening files
 */
public class fileSerializerOpener {
    /**
     * readGridSer. This class retrieves the saved grid from the file
     * @param name. name of the file
     * @return savedGrid
     */
    public Grid readGridSer (String name) {
        Grid savedGrid = null;
        FileInputStream fileIn;
    	try {
            //Opens file
           fileIn = new FileInputStream(name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            //Retrieves object
            savedGrid = (Grid) in.readObject();
            //Closes files
            in.close();
            fileIn.close();
     	
            //Catches the errors
      	} catch(FileNotFoundException exception){
              return null;
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        
        return savedGrid;
    }
}

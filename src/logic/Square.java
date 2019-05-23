/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models the squares that make the game
 */
public abstract class Square implements java.io.Serializable{
    //Attributte
    protected boolean flag;
    protected boolean rightClick;
    
    //Constructor
    public Square() {
        flag = false;
        rightClick = false;
    }
    
    //Changes the state of the right click variable to its contrary
    public void rightClick(){
        if (rightClick){
            rightClick = false;
        } else  {
            rightClick = true;
        }
        
    }
    
    //Sets the value of the flag variable
    public void leftClick(boolean newFlag) {
         flag = newFlag;
    }
    
    //Returns value of the variale flag
    public boolean getFlag() {
        return flag;
    } 
    
    //Returns value of the variable right click 
    public boolean getRightClick() {
        return rightClick;
    }
    
}

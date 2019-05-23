/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models a square number
 */
public class Number extends Square{
    private int number;
    
    //Constructor, sets the value of the number
    public Number(int newNumber) {
        number = newNumber;
    }
    
    //Gets value of the number
    public int getNumber() {
        return number;
    }
}

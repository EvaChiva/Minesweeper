package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logic.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @date: 30/abr/2019
 * @author Eva Cristina Beltrán Reyes, A01114495
 * Description: This class models the hard level of the minesweeper with a GUI approach
 */
public class hardLevel extends javax.swing.JFrame {
    
    //Attributtes
    private JButton gridButtons [][]; 
    private Grid grid;
    private boolean flag = false;
    private Game currentGame;

    
    /**
     * Creates new form easyLevel
     */
    public hardLevel() {
        initComponents();
        //For making buttons transparent
        saveButton.setOpaque(false);
        saveButton.setContentAreaFilled(false);
        saveButton.setBorderPainted(false);
        
        buttonNewGame.setOpaque(false);
        buttonNewGame.setContentAreaFilled(false);
        buttonNewGame.setBorderPainted(false);
        
        RestartButton.setOpaque(false);
        RestartButton.setContentAreaFilled(false);
        RestartButton.setBorderPainted(false);
        currentGame = new Game();
        
        //Creates a matrix of buttons
        this.gridButtons = new JButton[10][10];
        this.gridButtons = new JButton[][]{{square0_0, square0_1, square0_2, square0_3, square0_4, square0_5, square0_6, square0_7, square0_8, square0_9}, 
            {square1_0, square1_1, square1_2, square1_3, square1_4, square1_5, square1_6, square1_7, square1_8, square1_9}, 
            {square2_0, square2_1, square2_2, square2_3, square2_4, square2_5, square2_6, square2_7, square2_8, square2_9},
            {square3_0, square3_1, square3_2, square3_3, square3_4, square3_5, square3_6, square3_7, square3_8, square3_9},
            {square4_0, square4_1, square4_2, square4_3, square4_4, square4_5, square4_6, square4_7, square4_8, square4_9},
            {square5_0, square5_1, square5_2, square5_3, square5_4, square5_5, square5_6, square5_7, square5_8, square5_9},
            {square6_0, square6_1, square6_2, square6_3, square6_4, square6_5, square6_6, square6_7, square6_8, square6_9},
            {square7_0, square7_1, square7_2, square7_3, square7_4, square7_5, square7_6, square7_7, square7_8, square7_9},
            {square8_0, square8_1, square8_2, square8_3, square8_4, square8_5, square8_6, square8_7, square8_8, square8_9},
            {square9_0, square9_1, square9_2, square9_3, square9_4, square9_5, square9_6, square9_7, square9_8, square9_9}};
      
       
        //Creates a grid
        grid = new Grid(1);
       
        
        int i, j;
        int clickedSquares = 0;
       //Goes over all the matrix and assigns an action listener to evey button
        for(i = 0; i<10; i++){
            for (j = 0; j<10; j++ ){
                gridButtons[i][j].addActionListener(
        new ActionListener(){
       
        @Override
        public void actionPerformed (ActionEvent evt){
            
            boolean win = false;
            //Goes over all the matrix for finding wich button was clicked
            for (int i=0 ; i<10; i++) {
                for (int j= 0; j<10; j++) {
                    if(evt.getSource() == gridButtons[i][j]) {
                        if (flag) {
                            //If the flag button is activated, then the cell that
                            //is clicked will be set as a flag
                            gridButtons[i][j].setText("F");
                            gridButtons[i][j].setBackground(java.awt.Color.BLUE);
                            (grid.getSquare(i, j)).rightClick();
                            
                        } else if( !(grid.getSquare(i, j)).getRightClick()){
                            //If the flag button is not activated and the button
                            //is not set with a flag
                             grid.getSquare(i, j).leftClick(true);
                            if(grid.getSquare(i, j) instanceof Bomb){
                                //If the selected square is a bomb it will show itself
                                gridButtons[i][j].setText("*");
                                gridButtons[i][j].setBackground(java.awt.Color.RED);
                                
                                //shows the rest of the bombs after clicking one
                                int[] bombsPos = grid.lookForBombs(); 
                                for(int h = 0; h < bombsPos.length ;  h = h + 2) {
                                    gridButtons[bombsPos[h]][bombsPos[h+1]].setBackground(java.awt.Color.RED);
                                    gridButtons[bombsPos[h]][bombsPos[h+1]].setText("*");
                                    (grid.getSquare(bombsPos[h], bombsPos[h+1])).leftClick(true);
                                
                                }
                                //Informs the user that he or she lost
                                JOptionPane.showMessageDialog(null, " You lost! :(");
                                //Restarts the game
                                restart();
                                grid = currentGame.newGame(grid, (grid.getLevel()).getLevel());
                                
                        } else if (grid.getSquare(i, j) instanceof logic.Number) {
                            //If the clicked square is a number, it will show itself
                            gridButtons[i][j].setText(Integer.toString((((logic.Number)grid.getSquare(i, j)).getNumber())));
                            gridButtons[i][j].setBackground(java.awt.Color.yellow);
                            
                        } else {
                            //If the clicked square is a white space, it will show itself
                            gridButtons[i][j].setText(" ");
                            gridButtons[i][j].setBackground(java.awt.Color.PINK);
                            //Current white square position
                            int i0 = i;
                            int j0 = j;
                            
                            //Arrays for saving the positions of the next white spaces that must be checked
                            ArrayList<Integer> whitePositionsX = new ArrayList<Integer>();
                            ArrayList<Integer> whitePositionsY = new ArrayList<Integer>();
                            
                            do {
                                //Removes the positions that will be checked
                                if(whitePositionsX.size() > 0) {                                
                                    whitePositionsX.remove(0);
                                    whitePositionsY.remove(0);
                                    
                                }
                                //Saves the x and y positions that will be checked around the white square
                                int[] arrayX = new int[((WhiteSquare)grid.getSquare(i0,j0)).getX().length];
                                int[] arrayY = new int[((WhiteSquare)grid.getSquare(i0,j0)).getY().length];
                                arrayX = ((WhiteSquare)grid.getSquare(i0,j0)).getX();  
                                arrayY = ((WhiteSquare)grid.getSquare(i0,j0)).getY();
                                int x;
                                //Looks at all the positions in the array 
                                for(x = 0; x< arrayX.length; x++){
                                    if(grid.getSquare(arrayX[x],arrayY[x]) instanceof logic.Number) {
                                        //If the position contains a number, it will show itself
                                        gridButtons[arrayX[x]][arrayY[x]].setText(Integer.toString((((logic.Number)grid.getSquare(arrayX[x], arrayY[x])).getNumber())));
                                        gridButtons[arrayX[x]][arrayY[x]].setBackground(java.awt.Color.yellow);
                                        (grid.getSquare(arrayX[x], arrayY[x])).leftClick(true);
                                    } else {
                                        if( !(grid.getSquare(arrayX[x], arrayY[x])).getFlag()){
                                            //If the position contains a white square and has not been clicked on, it will show itself
                                            gridButtons[arrayX[x]][arrayY[x]].setText(" ");
                                            gridButtons[arrayX[x]][arrayY[x]].setBackground(java.awt.Color.PINK);
                                            //Also, it saves the position of the unclicked white square for checking it later
                                            whitePositionsX.add(arrayX[x]);
                                            whitePositionsY.add(arrayY[x]);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                            }
  
                                        }
                                    }
                                
                                //Makes the current white square position the next values in arrays
                                if(whitePositionsX.size() > 0) {
                                    i0 = whitePositionsX.get(0);
                                    j0 = whitePositionsY.get(0);
                                    (grid.getSquare(i0, j0)).leftClick(true);
                                    
                                }
                            //The loop stops until there are no more positions to check
                            } while(whitePositionsX.size() > 0);
                            

                        }
                    }
                        
                   win = currentGame.win(grid);
                   if(win) {
                        JOptionPane.showMessageDialog(null, " Congratulations, you won!!");
                    }
                        
                    }
                    
                    }
        
                }
                }
            } );
        }
        }
 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUpWindow = new javax.swing.JDesktopPane();
        bombContainer = new javax.swing.JPanel();
        square1_0 = new javax.swing.JButton();
        square1_1 = new javax.swing.JButton();
        square1_4 = new javax.swing.JButton();
        square1_3 = new javax.swing.JButton();
        square1_2 = new javax.swing.JButton();
        square1_6 = new javax.swing.JButton();
        square1_7 = new javax.swing.JButton();
        square1_5 = new javax.swing.JButton();
        square1_8 = new javax.swing.JButton();
        square1_9 = new javax.swing.JButton();
        square0_0 = new javax.swing.JButton();
        square0_1 = new javax.swing.JButton();
        square0_2 = new javax.swing.JButton();
        square0_3 = new javax.swing.JButton();
        square0_4 = new javax.swing.JButton();
        square0_5 = new javax.swing.JButton();
        square0_6 = new javax.swing.JButton();
        square0_7 = new javax.swing.JButton();
        square0_8 = new javax.swing.JButton();
        square0_9 = new javax.swing.JButton();
        square2_4 = new javax.swing.JButton();
        square2_3 = new javax.swing.JButton();
        square2_8 = new javax.swing.JButton();
        square2_2 = new javax.swing.JButton();
        square2_5 = new javax.swing.JButton();
        square2_0 = new javax.swing.JButton();
        square2_6 = new javax.swing.JButton();
        square2_7 = new javax.swing.JButton();
        square2_1 = new javax.swing.JButton();
        square2_9 = new javax.swing.JButton();
        square3_2 = new javax.swing.JButton();
        square3_7 = new javax.swing.JButton();
        square3_1 = new javax.swing.JButton();
        square3_8 = new javax.swing.JButton();
        square3_9 = new javax.swing.JButton();
        square3_0 = new javax.swing.JButton();
        square3_4 = new javax.swing.JButton();
        square3_6 = new javax.swing.JButton();
        square3_3 = new javax.swing.JButton();
        square3_5 = new javax.swing.JButton();
        square4_4 = new javax.swing.JButton();
        square4_8 = new javax.swing.JButton();
        square4_1 = new javax.swing.JButton();
        square4_2 = new javax.swing.JButton();
        square4_9 = new javax.swing.JButton();
        square4_3 = new javax.swing.JButton();
        square4_6 = new javax.swing.JButton();
        square4_7 = new javax.swing.JButton();
        square4_0 = new javax.swing.JButton();
        square4_5 = new javax.swing.JButton();
        square5_9 = new javax.swing.JButton();
        square5_8 = new javax.swing.JButton();
        square5_2 = new javax.swing.JButton();
        square5_7 = new javax.swing.JButton();
        square5_4 = new javax.swing.JButton();
        square5_3 = new javax.swing.JButton();
        square5_5 = new javax.swing.JButton();
        square5_6 = new javax.swing.JButton();
        square5_0 = new javax.swing.JButton();
        square5_1 = new javax.swing.JButton();
        square6_6 = new javax.swing.JButton();
        square6_1 = new javax.swing.JButton();
        square6_5 = new javax.swing.JButton();
        square6_7 = new javax.swing.JButton();
        square6_9 = new javax.swing.JButton();
        square6_8 = new javax.swing.JButton();
        square6_2 = new javax.swing.JButton();
        square6_4 = new javax.swing.JButton();
        square6_0 = new javax.swing.JButton();
        square6_3 = new javax.swing.JButton();
        square7_7 = new javax.swing.JButton();
        square7_1 = new javax.swing.JButton();
        square7_8 = new javax.swing.JButton();
        square7_6 = new javax.swing.JButton();
        square7_5 = new javax.swing.JButton();
        square7_3 = new javax.swing.JButton();
        square7_2 = new javax.swing.JButton();
        square7_4 = new javax.swing.JButton();
        square7_0 = new javax.swing.JButton();
        square7_9 = new javax.swing.JButton();
        square8_4 = new javax.swing.JButton();
        square8_6 = new javax.swing.JButton();
        square8_2 = new javax.swing.JButton();
        square8_0 = new javax.swing.JButton();
        square8_7 = new javax.swing.JButton();
        square8_3 = new javax.swing.JButton();
        square8_5 = new javax.swing.JButton();
        square8_9 = new javax.swing.JButton();
        square8_1 = new javax.swing.JButton();
        square8_8 = new javax.swing.JButton();
        square9_0 = new javax.swing.JButton();
        square9_1 = new javax.swing.JButton();
        square9_8 = new javax.swing.JButton();
        square9_4 = new javax.swing.JButton();
        square9_2 = new javax.swing.JButton();
        square9_7 = new javax.swing.JButton();
        square9_5 = new javax.swing.JButton();
        square9_9 = new javax.swing.JButton();
        square9_6 = new javax.swing.JButton();
        square9_3 = new javax.swing.JButton();
        buttonContainer = new javax.swing.JPanel();
        hardLabel = new javax.swing.JLabel();
        buttonNewGame = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        RestartButton = new javax.swing.JButton();
        bombLabel = new javax.swing.JLabel();
        flagButton = new javax.swing.JButton();
        flagLabel = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bombContainer.setOpaque(false);

        square1_0.setBackground(new java.awt.Color(102, 102, 102));

        square1_1.setBackground(new java.awt.Color(102, 102, 102));

        square1_4.setBackground(new java.awt.Color(102, 102, 102));

        square1_3.setBackground(new java.awt.Color(102, 102, 102));

        square1_2.setBackground(new java.awt.Color(102, 102, 102));

        square1_6.setBackground(new java.awt.Color(102, 102, 102));

        square1_7.setBackground(new java.awt.Color(102, 102, 102));

        square1_5.setBackground(new java.awt.Color(102, 102, 102));

        square1_8.setBackground(new java.awt.Color(102, 102, 102));

        square1_9.setBackground(new java.awt.Color(102, 102, 102));

        square0_0.setBackground(new java.awt.Color(102, 102, 102));

        square0_1.setBackground(new java.awt.Color(102, 102, 102));

        square0_2.setBackground(new java.awt.Color(102, 102, 102));

        square0_3.setBackground(new java.awt.Color(102, 102, 102));

        square0_4.setBackground(new java.awt.Color(102, 102, 102));

        square0_5.setBackground(new java.awt.Color(102, 102, 102));

        square0_6.setBackground(new java.awt.Color(102, 102, 102));

        square0_7.setBackground(new java.awt.Color(102, 102, 102));

        square0_8.setBackground(new java.awt.Color(102, 102, 102));

        square0_9.setBackground(new java.awt.Color(102, 102, 102));

        square2_4.setBackground(new java.awt.Color(102, 102, 102));

        square2_3.setBackground(new java.awt.Color(102, 102, 102));

        square2_8.setBackground(new java.awt.Color(102, 102, 102));

        square2_2.setBackground(new java.awt.Color(102, 102, 102));

        square2_5.setBackground(new java.awt.Color(102, 102, 102));

        square2_0.setBackground(new java.awt.Color(102, 102, 102));

        square2_6.setBackground(new java.awt.Color(102, 102, 102));

        square2_7.setBackground(new java.awt.Color(102, 102, 102));

        square2_1.setBackground(new java.awt.Color(102, 102, 102));

        square2_9.setBackground(new java.awt.Color(102, 102, 102));

        square3_2.setBackground(new java.awt.Color(102, 102, 102));

        square3_7.setBackground(new java.awt.Color(102, 102, 102));

        square3_1.setBackground(new java.awt.Color(102, 102, 102));

        square3_8.setBackground(new java.awt.Color(102, 102, 102));

        square3_9.setBackground(new java.awt.Color(102, 102, 102));

        square3_0.setBackground(new java.awt.Color(102, 102, 102));

        square3_4.setBackground(new java.awt.Color(102, 102, 102));

        square3_6.setBackground(new java.awt.Color(102, 102, 102));

        square3_3.setBackground(new java.awt.Color(102, 102, 102));

        square3_5.setBackground(new java.awt.Color(102, 102, 102));

        square4_4.setBackground(new java.awt.Color(102, 102, 102));

        square4_8.setBackground(new java.awt.Color(102, 102, 102));

        square4_1.setBackground(new java.awt.Color(102, 102, 102));

        square4_2.setBackground(new java.awt.Color(102, 102, 102));

        square4_9.setBackground(new java.awt.Color(102, 102, 102));

        square4_3.setBackground(new java.awt.Color(102, 102, 102));

        square4_6.setBackground(new java.awt.Color(102, 102, 102));

        square4_7.setBackground(new java.awt.Color(102, 102, 102));

        square4_0.setBackground(new java.awt.Color(102, 102, 102));

        square4_5.setBackground(new java.awt.Color(102, 102, 102));

        square5_9.setBackground(new java.awt.Color(102, 102, 102));

        square5_8.setBackground(new java.awt.Color(102, 102, 102));

        square5_2.setBackground(new java.awt.Color(102, 102, 102));

        square5_7.setBackground(new java.awt.Color(102, 102, 102));

        square5_4.setBackground(new java.awt.Color(102, 102, 102));

        square5_3.setBackground(new java.awt.Color(102, 102, 102));

        square5_5.setBackground(new java.awt.Color(102, 102, 102));

        square5_6.setBackground(new java.awt.Color(102, 102, 102));

        square5_0.setBackground(new java.awt.Color(102, 102, 102));

        square5_1.setBackground(new java.awt.Color(102, 102, 102));

        square6_6.setBackground(new java.awt.Color(102, 102, 102));

        square6_1.setBackground(new java.awt.Color(102, 102, 102));

        square6_5.setBackground(new java.awt.Color(102, 102, 102));

        square6_7.setBackground(new java.awt.Color(102, 102, 102));

        square6_9.setBackground(new java.awt.Color(102, 102, 102));

        square6_8.setBackground(new java.awt.Color(102, 102, 102));

        square6_2.setBackground(new java.awt.Color(102, 102, 102));

        square6_4.setBackground(new java.awt.Color(102, 102, 102));

        square6_0.setBackground(new java.awt.Color(102, 102, 102));

        square6_3.setBackground(new java.awt.Color(102, 102, 102));

        square7_7.setBackground(new java.awt.Color(102, 102, 102));

        square7_1.setBackground(new java.awt.Color(102, 102, 102));

        square7_8.setBackground(new java.awt.Color(102, 102, 102));

        square7_6.setBackground(new java.awt.Color(102, 102, 102));

        square7_5.setBackground(new java.awt.Color(102, 102, 102));

        square7_3.setBackground(new java.awt.Color(102, 102, 102));

        square7_2.setBackground(new java.awt.Color(102, 102, 102));

        square7_4.setBackground(new java.awt.Color(102, 102, 102));

        square7_0.setBackground(new java.awt.Color(102, 102, 102));

        square7_9.setBackground(new java.awt.Color(102, 102, 102));

        square8_4.setBackground(new java.awt.Color(102, 102, 102));

        square8_6.setBackground(new java.awt.Color(102, 102, 102));

        square8_2.setBackground(new java.awt.Color(102, 102, 102));

        square8_0.setBackground(new java.awt.Color(102, 102, 102));

        square8_7.setBackground(new java.awt.Color(102, 102, 102));

        square8_3.setBackground(new java.awt.Color(102, 102, 102));

        square8_5.setBackground(new java.awt.Color(102, 102, 102));

        square8_9.setBackground(new java.awt.Color(102, 102, 102));

        square8_1.setBackground(new java.awt.Color(102, 102, 102));

        square8_8.setBackground(new java.awt.Color(102, 102, 102));

        square9_0.setBackground(new java.awt.Color(102, 102, 102));

        square9_1.setBackground(new java.awt.Color(102, 102, 102));

        square9_8.setBackground(new java.awt.Color(102, 102, 102));

        square9_4.setBackground(new java.awt.Color(102, 102, 102));

        square9_2.setBackground(new java.awt.Color(102, 102, 102));

        square9_7.setBackground(new java.awt.Color(102, 102, 102));

        square9_5.setBackground(new java.awt.Color(102, 102, 102));

        square9_9.setBackground(new java.awt.Color(102, 102, 102));

        square9_6.setBackground(new java.awt.Color(102, 102, 102));

        square9_3.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout bombContainerLayout = new javax.swing.GroupLayout(bombContainer);
        bombContainer.setLayout(bombContainerLayout);
        bombContainerLayout.setHorizontalGroup(
            bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bombContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square0_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square4_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square4_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square5_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square5_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square6_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square6_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square7_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square7_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square8_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square8_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bombContainerLayout.createSequentialGroup()
                        .addComponent(square9_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(square9_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        bombContainerLayout.setVerticalGroup(
            bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bombContainerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square0_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square2_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square3_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square3_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square4_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square4_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square5_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square5_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square6_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square6_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square7_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square7_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square8_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square8_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bombContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(square9_0, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(square9_9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        buttonContainer.setBackground(new java.awt.Color(255, 255, 204));
        buttonContainer.setOpaque(false);

        hardLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/hard game.jpeg"))); // NOI18N

        buttonNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/new game.jpeg"))); // NOI18N
        buttonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewGameActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/save game.jpeg"))); // NOI18N
        saveButton.setToolTipText("");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        RestartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/restart.jpeg"))); // NOI18N
        RestartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RestartButtonMouseClicked(evt);
            }
        });

        bombLabel.setFont(new java.awt.Font("Chalkboard SE", 0, 18)); // NOI18N
        bombLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/bomba.jpeg"))); // NOI18N

        flagButton.setFont(new java.awt.Font("MS Gothic", 0, 24)); // NOI18N
        flagButton.setText("FLAG");
        flagButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flagButtonMouseClicked(evt);
            }
        });

        flagLabel.setText("False");

        javax.swing.GroupLayout buttonContainerLayout = new javax.swing.GroupLayout(buttonContainer);
        buttonContainer.setLayout(buttonContainerLayout);
        buttonContainerLayout.setHorizontalGroup(
            buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RestartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonNewGame)
                .addGap(201, 201, 201))
            .addGroup(buttonContainerLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(bombLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(67, 67, 67)
                .addGroup(buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flagButton)
                    .addGroup(buttonContainerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(flagLabel)))
                .addGap(255, 255, 255))
        );
        buttonContainerLayout.setVerticalGroup(
            buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonContainerLayout.createSequentialGroup()
                .addGroup(buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bombLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(buttonContainerLayout.createSequentialGroup()
                        .addComponent(flagButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flagLabel)))
                .addGroup(buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonContainerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(saveButton))
                    .addGroup(buttonContainerLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(buttonContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RestartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        background.setIcon(new javax.swing.ImageIcon("/Users/evabeltran/Downloads/fondoLibretaRayada.jpg")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(bombContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(buttonContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(buttonContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(bombContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
        
    
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        String name;
         //setting a jOptionPanel for the user to write the file's name for saving the game
        name = JOptionPane.showInputDialog("Type your file's name. Please don not add any extension");
        //confirmation for the user
        JOptionPane.showMessageDialog(null, "Your file\n" +
"         was saved as: " + name);
        //creating a fileWriter object
        fileSerialiazer file = new fileSerialiazer();    
        //serializing
        file.serializeGrid(name, grid);
        
    }//GEN-LAST:event_saveButtonMouseClicked

    private void buttonNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewGameActionPerformed
        // Erases all the actions made by the user
        restart();
        // Gets a new grid
        grid = currentGame.newGame(grid, (grid.getLevel()).getLevel());
    }//GEN-LAST:event_buttonNewGameActionPerformed

    private void RestartButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestartButtonMouseClicked
        // Erases all the actions made by the user
        restart();
    }//GEN-LAST:event_RestartButtonMouseClicked

    private void flagButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flagButtonMouseClicked
        //If the flag is originally true; then after clicking the button should change to false and viceversa
        //Is fot controlling when the user can place flag
        if(flag) {
            flag = false;
            flagLabel.setText("False");
        } else {
            flag = true;
            flagLabel.setText("True");
        }

    }//GEN-LAST:event_flagButtonMouseClicked

    private void restart () {
        int i, j;
        //Goes through the matrix and restarts to their original settings all the squares
        for(i = 0; i <(grid.getLevel()).getColumns() ; i++) {
            for(j = 0; j <(grid.getLevel()).getRows() ; j++) {
                (grid.getSquare(i, j)).leftClick(false);
                gridButtons[i][j].setText(" ");
                gridButtons[i][j].setBackground(java.awt.Color.GRAY);
                if((grid.getSquare(i, j)).getRightClick()) {
                    (grid.getSquare(i, j)).rightClick();
                }
            }
        }
    }
    
    public void setGame (Grid gameGrid) {
        //This method sets the game according to a received Grid
        //Changes current grid to the received grid
        grid = null;
        grid = gameGrid;
       //Goes through grid matrix and sets buttons according to the saved features
        int i, j;
        for(i = 0; i <(gameGrid.getLevel()).getColumns(); i++) {
            for(j = 0; j <(gameGrid.getLevel()).getRows(); j++) {
                if(grid.getSquare(i, j).getFlag()) {
                    //if the buttons was previously clicked it will show itself according to the type of square it is
                    if (grid.getSquare(i, j) instanceof Bomb) {
                    gridButtons[i][j].setText("*");
                    gridButtons[i][j].setBackground(java.awt.Color.RED);
                    } else if (grid.getSquare(i, j) instanceof logic.Number){ 
                    gridButtons[i][j].setText(Integer.toString((((logic.Number)grid.getSquare(i, j)).getNumber())));
                    gridButtons[i][j].setBackground(java.awt.Color.YELLOW);
                    } else {
                    gridButtons[i][j].setText(" ");
                    gridButtons[i][j].setBackground(java.awt.Color.PINK);
                    }
                }
                if(grid.getSquare(i, j).getRightClick()){
                    //If the square had a flag, it will show itself as a flag
                    gridButtons[i][j].setText("P");
                    gridButtons[i][j].setBackground(java.awt.Color.BLUE);
                }
                
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(hardLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hardLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hardLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hardLevel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hardLevel().setVisible(true);
            }
        });
        
        
  
        
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RestartButton;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bombContainer;
    private javax.swing.JLabel bombLabel;
    private javax.swing.JPanel buttonContainer;
    private javax.swing.JButton buttonNewGame;
    private javax.swing.JButton flagButton;
    private javax.swing.JLabel flagLabel;
    private javax.swing.JLabel hardLabel;
    private javax.swing.JDesktopPane popUpWindow;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton square0_0;
    private javax.swing.JButton square0_1;
    private javax.swing.JButton square0_2;
    private javax.swing.JButton square0_3;
    private javax.swing.JButton square0_4;
    private javax.swing.JButton square0_5;
    private javax.swing.JButton square0_6;
    private javax.swing.JButton square0_7;
    private javax.swing.JButton square0_8;
    private javax.swing.JButton square0_9;
    private javax.swing.JButton square1_0;
    private javax.swing.JButton square1_1;
    private javax.swing.JButton square1_2;
    private javax.swing.JButton square1_3;
    private javax.swing.JButton square1_4;
    private javax.swing.JButton square1_5;
    private javax.swing.JButton square1_6;
    private javax.swing.JButton square1_7;
    private javax.swing.JButton square1_8;
    private javax.swing.JButton square1_9;
    private javax.swing.JButton square2_0;
    private javax.swing.JButton square2_1;
    private javax.swing.JButton square2_2;
    private javax.swing.JButton square2_3;
    private javax.swing.JButton square2_4;
    private javax.swing.JButton square2_5;
    private javax.swing.JButton square2_6;
    private javax.swing.JButton square2_7;
    private javax.swing.JButton square2_8;
    private javax.swing.JButton square2_9;
    private javax.swing.JButton square3_0;
    private javax.swing.JButton square3_1;
    private javax.swing.JButton square3_2;
    private javax.swing.JButton square3_3;
    private javax.swing.JButton square3_4;
    private javax.swing.JButton square3_5;
    private javax.swing.JButton square3_6;
    private javax.swing.JButton square3_7;
    private javax.swing.JButton square3_8;
    private javax.swing.JButton square3_9;
    private javax.swing.JButton square4_0;
    private javax.swing.JButton square4_1;
    private javax.swing.JButton square4_2;
    private javax.swing.JButton square4_3;
    private javax.swing.JButton square4_4;
    private javax.swing.JButton square4_5;
    private javax.swing.JButton square4_6;
    private javax.swing.JButton square4_7;
    private javax.swing.JButton square4_8;
    private javax.swing.JButton square4_9;
    private javax.swing.JButton square5_0;
    private javax.swing.JButton square5_1;
    private javax.swing.JButton square5_2;
    private javax.swing.JButton square5_3;
    private javax.swing.JButton square5_4;
    private javax.swing.JButton square5_5;
    private javax.swing.JButton square5_6;
    private javax.swing.JButton square5_7;
    private javax.swing.JButton square5_8;
    private javax.swing.JButton square5_9;
    private javax.swing.JButton square6_0;
    private javax.swing.JButton square6_1;
    private javax.swing.JButton square6_2;
    private javax.swing.JButton square6_3;
    private javax.swing.JButton square6_4;
    private javax.swing.JButton square6_5;
    private javax.swing.JButton square6_6;
    private javax.swing.JButton square6_7;
    private javax.swing.JButton square6_8;
    private javax.swing.JButton square6_9;
    private javax.swing.JButton square7_0;
    private javax.swing.JButton square7_1;
    private javax.swing.JButton square7_2;
    private javax.swing.JButton square7_3;
    private javax.swing.JButton square7_4;
    private javax.swing.JButton square7_5;
    private javax.swing.JButton square7_6;
    private javax.swing.JButton square7_7;
    private javax.swing.JButton square7_8;
    private javax.swing.JButton square7_9;
    private javax.swing.JButton square8_0;
    private javax.swing.JButton square8_1;
    private javax.swing.JButton square8_2;
    private javax.swing.JButton square8_3;
    private javax.swing.JButton square8_4;
    private javax.swing.JButton square8_5;
    private javax.swing.JButton square8_6;
    private javax.swing.JButton square8_7;
    private javax.swing.JButton square8_8;
    private javax.swing.JButton square8_9;
    private javax.swing.JButton square9_0;
    private javax.swing.JButton square9_1;
    private javax.swing.JButton square9_2;
    private javax.swing.JButton square9_3;
    private javax.swing.JButton square9_4;
    private javax.swing.JButton square9_5;
    private javax.swing.JButton square9_6;
    private javax.swing.JButton square9_7;
    private javax.swing.JButton square9_8;
    private javax.swing.JButton square9_9;
    // End of variables declaration//GEN-END:variables
}

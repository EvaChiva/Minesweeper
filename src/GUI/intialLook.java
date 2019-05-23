package GUI;

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
 * Description: This class models the first screen that will appear to the user
 */
public class intialLook extends javax.swing.JFrame {

    /**
     * Creates new form intialLook
     */
    public intialLook() {
        initComponents();
        //Makes the buttons transparent
        easyButton.setOpaque(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setBorderPainted(false);
        
        hardButton.setOpaque(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setBorderPainted(false);
        
        loadGameButton.setOpaque(false);
        loadGameButton.setContentAreaFilled(false);
        loadGameButton.setBorderPainted(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        easyButton = new javax.swing.JButton();
        hardButton = new javax.swing.JButton();
        chooseLabel = new javax.swing.JLabel();
        loadLabel = new javax.swing.JLabel();
        loadGameButton = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        container.setOpaque(false);

        easyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/easy.jpeg"))); // NOI18N
        easyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                easyButtonMouseClicked(evt);
            }
        });

        hardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/hard.jpeg"))); // NOI18N
        hardButton.setActionCommand("Hard ");
        hardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hardButtonMouseClicked(evt);
            }
        });

        chooseLabel.setFont(new java.awt.Font("MS Gothic", 0, 14)); // NOI18N
        chooseLabel.setText("Please choose the level you want to play");

        loadLabel.setFont(new java.awt.Font("MS Gothic", 0, 18)); // NOI18N
        loadLabel.setText("Or load a previous game");

        loadGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/ImagesMineSweeper/load game.jpeg"))); // NOI18N
        loadGameButton.setActionCommand("Hard ");
        loadGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadGameButtonMouseClicked(evt);
            }
        });
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(chooseLabel))
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hardButton)
                            .addComponent(easyButton))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(loadGameButton))
                    .addComponent(loadLabel))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(chooseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(easyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hardButton)
                .addGap(35, 35, 35)
                .addComponent(loadLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadGameButton)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/fondoLibretaRayada.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void easyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_easyButtonMouseClicked
        //Creates and opens an easy level
        easyLevel easy = new easyLevel ();
        easy.setVisible(true);
        
    }//GEN-LAST:event_easyButtonMouseClicked

    private void hardButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hardButtonMouseClicked
        //Creates and opens a hard level
        hardLevel hard = new hardLevel ();
        hard.setVisible(true);
    }//GEN-LAST:event_hardButtonMouseClicked

    private void loadGameButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadGameButtonMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_loadGameButtonMouseClicked

    private void loadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameButtonActionPerformed

         String name;
        //setting a jOptionPanel for the user to write the file's that will be open
        name = JOptionPane.showInputDialog("Type your file's name");
        //Showing user what file will be opened
        JOptionPane.showMessageDialog(null, "Your will open the next file " + name + ".ser");
        
        // creating a fileSerializerOpener object
        fileSerializerOpener file = new fileSerializerOpener();
        Grid grid = file.readGridSer(name); 
        if(grid == null){
            JOptionPane.showMessageDialog(null, "The typed file does not exist"); 
        }else if((grid.getLevel()).getLevel() == 1 ){
            hardLevel easy = new hardLevel ();
            easy.setVisible(true);
            easy.setGame(grid);
        } else {
            easyLevel easy = new easyLevel ();
            easy.setVisible(true);
            easy.setGame(grid);
       }
        
        //creating a ImageIcon object and serializing the icon
        
    }//GEN-LAST:event_loadGameButtonActionPerformed

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
            java.util.logging.Logger.getLogger(intialLook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(intialLook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(intialLook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(intialLook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new intialLook().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel chooseLabel;
    private javax.swing.JPanel container;
    private javax.swing.JButton easyButton;
    private javax.swing.JButton hardButton;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JLabel loadLabel;
    // End of variables declaration//GEN-END:variables
}
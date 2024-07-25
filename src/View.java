import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class View extends JPanel{
 
    // initialise board and display
    //fetch data from controller and display to window
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    Board board = new Board(); 
    Model model;
    Controller controller;
    
    private JLabel messageLabel;
    private JPanel[][] boardPanels;
    

    public View(){
    }
    

    public void initialise(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        createFrame();
    }



    private void createFrame() {
    	
    	
        JFrame playerFrame = new JFrame("Chess");
        boardPanels = new JPanel[8][8];
        
        playerFrame.setVisible(true);

    	board.createBoard(1, boardPanels, controller, playerFrame);


        messageLabel = new JLabel();
        messageLabel.setText("WHITE PLAYERS TURN");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
        messageLabel.setForeground(Color.white);
        messageLabel.setBackground(new Color(72, 72, 72));
        playerFrame.add(messageLabel, BorderLayout.NORTH);

    }


    public void feedback_to_user(String message){
        messageLabel.setText(message);
    }



    

    public void update(){

    }





}


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
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
    private JLabel whitePiecesLabel;
    private JLabel blackPiecesLabel;
    private JPanel[][] boardPanels;
    

    public View(){
        board = new Board();
    }
    

    public void initialise(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        createFrame();
    }



    private void createFrame() {
    	
        JFrame playerFrame = new JFrame("Chess");
        boardPanels = new JPanel[8][8];
        
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setSize(WIDTH+16, HEIGHT+38);
        playerFrame.setLayout(null);
        playerFrame.getContentPane().setBackground(new Color(50, 50, 50));
        playerFrame.setLocationRelativeTo(null);

        board.setBounds(0, 0, 800, 800);
    	board.createBoard(1, boardPanels, controller);
        playerFrame.add(board);

        

        messageLabel = new JLabel();
        messageLabel.setText("WHITE PLAYER- select piece to move");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
        messageLabel.setForeground(Color.white);
        messageLabel.setBackground(new Color(72, 72, 72));
        messageLabel.setBounds(802, 3, WIDTH-805, 50);  // Set position and size for the label
        playerFrame.add(messageLabel);

        Icon icon = new ImageIcon("../rec/Restart.png");
        JButton restartButton = new JButton(icon);
        restartButton.setBounds(802, 3+52, 50, 50);
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);
        restartButton.setBackground(new Color(72, 72, 72));
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        restartButton.addActionListener(e -> controller.startup());

        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
        });
        playerFrame.add(restartButton);

        whitePiecesLabel = new JLabel();
        whitePiecesLabel.setText("White Pieces Captured");
        whitePiecesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        whitePiecesLabel.setOpaque(false);
        whitePiecesLabel.setForeground(Color.white);
        whitePiecesLabel.setBounds(810, 103, WIDTH-805, 50);  // Set position and size for the label
        playerFrame.add(whitePiecesLabel);


        blackPiecesLabel = new JLabel();
        blackPiecesLabel.setText("Black Pieces Captured");
        blackPiecesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        blackPiecesLabel.setOpaque(false);
        blackPiecesLabel.setForeground(Color.white);
        blackPiecesLabel.setBounds(810, 303, WIDTH-805, 50);  // Set position and size for the label
        playerFrame.add(blackPiecesLabel);

        playerFrame.setVisible(true);
    }


    public void feedback_to_user(String message){
        messageLabel.setText(message);
    }



    

    public void update(){
        int width = model.getBoardWidth();
        int height = model.getBoardHeight();
        for ( int x = 0 ; x < width ; x++ ) {
		    for ( int y = 0 ; y < height ; y++ ) {
                switch (model.getBoardContents(x, y)) { // add / remove images onto square
                    case 0:
                        board.removePiece(x, y,  boardPanels);
                        break;
                    case 1:
                        board.placePiece(x, y, "../rec/piece/w-pawn.png", boardPanels);
                        break;
                    case 2:
                        board.placePiece(x, y, "../rec/piece/w-rook.png", boardPanels);
                        break;
                    case 3:
                        board.placePiece(x, y, "../rec/piece/w-knight.png", boardPanels);
                        break;
                    case 4:
                        board.placePiece(x, y, "../rec/piece/w-bishop.png", boardPanels);
                        break;
                    case 5:
                        board.placePiece(x, y, "../rec/piece/w-queen.png", boardPanels);
                        break;
                    case 6:
                        board.placePiece(x, y, "../rec/piece/w-king.png", boardPanels);
                        break;
                    case 7:
                        board.placePiece(x, y, "../rec/piece/b-pawn.png", boardPanels);
                        break;
                    case 8:
                        board.placePiece(x, y, "../rec/piece/b-rook.png", boardPanels);
                        break;
                    case 9:
                        board.placePiece(x, y, "../rec/piece/b-knight.png", boardPanels);

                        break;
                    case 10:
                        board.placePiece(x, y, "../rec/piece/b-bishop.png", boardPanels);
                        break;
                    case 11:
                        board.placePiece(x, y, "../rec/piece/b-queen.png", boardPanels);
                        break;
                    case 12:
                        board.placePiece(x, y, "../rec/piece/b-king.png", boardPanels);
                        break;

                    
                }
            }
        }

    }





}


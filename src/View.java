import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.GridLayout;


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
    private JPanel whitePiecesPanel;
    private JPanel blackPiecesPanel;
    private JPanel[][] boardPanels;
    

    public View(){
        board = new Board();
    }
    

    public void initialise(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        model.setPlayer(1);
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
        System.out.println(model.getPlayer());

        board.setBounds(0, 0, 800, 800);
    	board.createBoard(model.getPlayer(), boardPanels, controller);
        playerFrame.add(board);

        

        messageLabel = new JLabel();
        messageLabel.setText("WHITE PLAYER- select piece to move");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setOpaque(true);
        messageLabel.setForeground(Color.white);
        messageLabel.setBackground(new Color(72, 72, 72));
        messageLabel.setBounds(802, 3, WIDTH-804, 50);  // Set position and size for the label
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

        JLabel whitePiecesLabel = new JLabel();
        whitePiecesLabel.setText("White Pieces Captured");
        whitePiecesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        whitePiecesLabel.setOpaque(false);
        whitePiecesLabel.setForeground(Color.white);
        whitePiecesLabel.setBounds(810, 103, WIDTH - 805, 50);  // Set position and size for the label
        playerFrame.add(whitePiecesLabel);

        whitePiecesPanel = new JPanel();
        whitePiecesPanel.setBounds(802, 138, WIDTH - 804, 150);
        whitePiecesPanel.setBackground(new Color(72, 72, 72));
        whitePiecesPanel.setLayout(new GridLayout(0, 1));
        playerFrame.add(whitePiecesPanel);

        JLabel blackPiecesLabel = new JLabel();
        blackPiecesLabel.setText("Black Pieces Captured");
        blackPiecesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        blackPiecesLabel.setOpaque(false);
        blackPiecesLabel.setForeground(Color.white);
        blackPiecesLabel.setBounds(810, 303, WIDTH - 805, 50);  // Set position and size for the label
        playerFrame.add(blackPiecesLabel);

        blackPiecesPanel = new JPanel();
        blackPiecesPanel.setBounds(802, 338, WIDTH - 804, 150);
        blackPiecesPanel.setBackground(new Color(72, 72, 72));
        blackPiecesPanel.setLayout(new GridLayout(0, 1));
        playerFrame.add(blackPiecesPanel);

        playerFrame.setVisible(true);
    }

    public void addPieceMessage(String pieceName, int amountTaken, int player) {
        JLabel pieceLabel = new JLabel(pieceName + ": " + amountTaken);
        pieceLabel.setForeground(Color.white);
        if (player == 0) {
            whitePiecesPanel.add(pieceLabel);
            whitePiecesPanel.revalidate();
            whitePiecesPanel.repaint();
        } else {
            blackPiecesPanel.add(pieceLabel);
            blackPiecesPanel.revalidate();
            blackPiecesPanel.repaint();
        }
        
        
    }

    public void clearPanels(){
        
            whitePiecesPanel.removeAll();
            whitePiecesPanel.revalidate();
            whitePiecesPanel.repaint();
        
            blackPiecesPanel.removeAll();
            blackPiecesPanel.revalidate();
            blackPiecesPanel.repaint();
        
    }

    public void feedback_to_user(String message){
        messageLabel.setText(message);
    }




    public void update(){
        int width = model.getBoardWidth();
        int height = model.getBoardHeight();
        for ( int row = 0 ; row < width ; row++ ) {
		    for ( int col = 0 ; col < height ; col++ ) {
                switch (model.getBoardContents(row, col)) {
                    case 0:
                        board.removePiece(row, col,  boardPanels);
                        break;
                    case 1:
                        board.placePiece(row, col, "../rec/piece/w-pawn.png", boardPanels);
                        break;
                    case 2:
                        board.placePiece(row, col, "../rec/piece/w-rook.png", boardPanels);
                        break;
                    case 3:
                        board.placePiece(row, col, "../rec/piece/w-knight.png", boardPanels);
                        break;
                    case 4:
                        board.placePiece(row, col, "../rec/piece/w-bishop.png", boardPanels);
                        break;
                    case 5:
                        board.placePiece(row, col, "../rec/piece/w-queen.png", boardPanels);
                        break;
                    case 6:
                        board.placePiece(row, col, "../rec/piece/w-king.png", boardPanels);
                        break;
                    case 7:
                        board.placePiece(row, col, "../rec/piece/b-pawn.png", boardPanels);
                        break;
                    case 8:
                        board.placePiece(row, col, "../rec/piece/b-rook.png", boardPanels);
                        break;
                    case 9:
                        board.placePiece(row, col, "../rec/piece/b-knight.png", boardPanels);
                        break;
                    case 10:
                        board.placePiece(row, col, "../rec/piece/b-bishop.png", boardPanels);
                        break;
                    case 11:
                        board.placePiece(row, col, "../rec/piece/b-queen.png", boardPanels);
                        break;
                    case 12:
                        board.placePiece(row, col, "../rec/piece/b-king.png", boardPanels);
                        break;

                    
                }
            }
        }

    }





}


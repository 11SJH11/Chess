import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;

    
    
    public Board(){
       
    }

    


    public void createBoard(int player, JPanel[][] boardPanels, Controller controller, JFrame playerFrame) {
		
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setLayout(new BorderLayout());
        playerFrame.setSize(1000, 1000);
        playerFrame.getContentPane().setBackground(Color.WHITE);
        playerFrame.setLocationRelativeTo(null);

        JPanel boardPanel = new JPanel(new GridLayout(MAX_ROW, MAX_COL)); 
        boardPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(72, 72, 72)));
        playerFrame.add(boardPanel, BorderLayout.CENTER);
        
        
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                JPanel cellPanel = new JPanel();
                if ((col % 2 != 0 && row % 2 == 0)||(col % 2 == 0 && row % 2 != 0)) {
                    cellPanel.setBackground(new Color(125, 135, 150));
                }
                else{
                    cellPanel.setBackground(new Color(232, 235, 239));
                }
                 
                cellPanel.setBorder(null);
                boardPanel.add(cellPanel);
                boardPanels[row][col] = cellPanel;

                int finalRow = row;
                int finalCol = col;
                cellPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) { 
                        controller.squareSelected(player, finalRow, finalCol); }
                });
            }
        }
	}


    public void placePiece(){

    }


    


}

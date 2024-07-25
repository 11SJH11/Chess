import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

    
    
    public Board() {
        setLayout(new GridLayout(MAX_ROW, MAX_COL));
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(72, 72, 72)));
    }

    public void createBoard(int player, JPanel[][] boardPanels, Controller controller) {
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COL; col++) {
                JPanel cellPanel = new JPanel();
                if ((col % 2 != 0 && row % 2 == 0) || (col % 2 == 0 && row % 2 != 0)) {
                    cellPanel.setBackground(new Color(125, 135, 150));
                } else {
                    cellPanel.setBackground(new Color(232, 235, 239));
                }

                cellPanel.setBorder(null);
                add(cellPanel);
                boardPanels[row][col] = cellPanel;

                int finalRow = row;
                int finalCol = col;
                cellPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.squareSelected(player, finalRow, finalCol);
                    }
                });
            }
        }
    }


    public void placePiece(int row, int col, String pieceImagePath, JPanel[][] boardPanels) {
        JLabel pieceLabel = new JLabel(new ImageIcon(pieceImagePath));
        boardPanels[row][col].removeAll();
        boardPanels[row][col].add(pieceLabel);
        boardPanels[row][col].revalidate();
        boardPanels[row][col].repaint();
    }

    public void removePiece(int row, int col, JPanel[][] boardPanels) {
        boardPanels[row][col].removeAll();
        boardPanels[row][col].revalidate();
        boardPanels[row][col].repaint();
    }


    


}

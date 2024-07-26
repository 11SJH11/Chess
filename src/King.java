public class King extends Piece {
    public King(int x, int y, int player) {
        super(x, y, player);
    }


    public int isValidMove(int fromRow, int fromCol, int toRow, int toCol, Model model) {
        int piece = model.getBoardContents(toRow, toCol);

        int playerRow = player == 1? 7:0;

        int leftCastle = model.getBoardContents(playerRow, 1) +
                         model.getBoardContents(playerRow, 2) + 
                         model.getBoardContents(playerRow, 3);

        int rightCastle = model.getBoardContents(playerRow, 5) +
                         model.getBoardContents(playerRow, 6);

        int deltaX = Math.abs(toRow - fromRow);
        int deltaY = Math.abs(toCol - fromCol);

        
        // Castle white left
        if (player == 1 && leftCastle == 0 && (model.hasWhiteCastled() == false) && (playerRow == toRow && toCol == 2)) {
            return -1;
        }

        // Castle white right
        if (player == 1 && rightCastle == 0 && (model.hasWhiteCastled() == false) && (playerRow == toRow && toCol == 6)) {
            return -2;
        }
        
        // Castle black left
        if (player == 0 && leftCastle == 0 && (model.hasBlackCastled() == false) && (playerRow == toRow && toCol == 2)) {
            return -1;
        }

        // Castle black right
        if (player == 0 && rightCastle == 0 && (model.hasBlackCastled() == false) && (playerRow == toRow && toCol == 6)) {
            return -2;
        }
                
    

        // King can move one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return 0;
        }
        
        if (piece != 0 && ((piece <= 6 && player == 1) || (piece >= 7 && player == 0))) {
            return 0;
        }
        return 1;
    }
}
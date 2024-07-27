public class Pawn extends Piece {

    public Pawn(int x, int y, int player) {
        super(x, y, player);
    }


    //check pawn moves are valid
    
    @Override
    public int isValidMove(int fromRow, int fromCol, int toRow, int toCol, Model model) {
        int piece = model.getBoardContents(toRow, toCol);
        int opponentPawn = (player == 1) ? 7 : 1;
        if (player == 1) { // White Pawn

            if (toRow == 0 && ((fromCol == toCol && fromRow - toRow == 1 && piece == 0) || (Math.abs(fromCol - toCol) == 1 && fromRow - toRow == 1 && piece > 6))) {
                return 2; //promotion
            }

            if (fromCol == toCol && fromRow - toRow == 1 && piece == 0) {
                return 1; // Move forward
            }
            if (fromCol == toCol && fromRow == 6 && fromRow - toRow == 2 && piece == 0 && model.getBoardContents(toRow+1, toCol) == 0) {
                return 1; // Double move forward
            }
            if (Math.abs(fromCol - toCol) == 1 && fromRow - toRow == 1 && piece > 6) {
                return 1; // Capture
            }

            // En passant for White
            if (fromRow == 3 && toRow == 2 && Math.abs(fromCol - toCol) == 1 && piece == 0) {
                int adjacentPawn = model.getBoardContents(fromRow, toCol);
                boolean lastMoveDoublePawnMove = model.isLastMoveDoublePawnMove(toCol, fromRow);
                if (adjacentPawn == opponentPawn && lastMoveDoublePawnMove) {
                    return 3; // En passant
                }
            }
        } else if (player == 0) { // Black Pawn

            if (toRow == 7 && ((fromCol == toCol && toRow - fromRow == 1 && piece == 0) || (Math.abs(fromCol - toCol) == 1 && toRow - fromRow == 1 && piece > 0 && piece <= 6))) {
                return 2; //promotion
            }
            if (fromCol == toCol && toRow - fromRow == 1 && piece == 0) {
                return 1; // Move forward
            }
            if (fromCol == toCol && fromRow == 1 && toRow - fromRow == 2 && piece == 0) {
                return 1; // Double move forward
            }
            if (Math.abs(fromCol - toCol) == 1 && toRow - fromRow == 1 && piece > 0 && piece <= 6) {
                return 1; // Capture
            }

            if (fromRow == 4 && toRow == 5 && Math.abs(fromCol - toCol) == 1 && piece == 0) {
                int adjacentPawn = model.getBoardContents(fromRow, toCol);
                boolean lastMoveDoublePawnMove = model.isLastMoveDoublePawnMove(toCol, fromRow);
                if (adjacentPawn == opponentPawn && lastMoveDoublePawnMove) {
                    return 3; // En passant
                }
            }
            
        }
        return 0;
    }
}

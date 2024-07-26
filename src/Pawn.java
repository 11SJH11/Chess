public class Pawn extends Piece {

    public Pawn(int x, int y, int player) {
        super(x, y, player);
    }





    //check pawn moves are valid
    
    @Override
    public int isValidMove(int fromY, int fromX, int toY, int toX, Model model) {
        int piece = model.getBoardContents(toY, toX);
        int opponentPawn = (player == 1) ? 7 : 1;
        if (player == 1) { // White Pawn

            if (toY == 0 && ((fromX == toX && fromY - toY == 1 && piece == 0) || (Math.abs(fromX - toX) == 1 && fromY - toY == 1 && piece > 6))) {
                return 2; //promotion
            }

            if (fromX == toX && fromY - toY == 1 && piece == 0) {
                return 1; // Move forward
            }
            if (fromX == toX && fromY == 6 && fromY - toY == 2 && piece == 0 && model.getBoardContents(toY+1, toX) == 0) {
                return 1; // Double move forward
            }
            if (Math.abs(fromX - toX) == 1 && fromY - toY == 1 && piece > 6) {
                return 1; // Capture
            }

            // En passant for White
            if (fromY == 3 && toY == 2 && Math.abs(fromX - toX) == 1 && piece == 0) {
                int adjacentPawn = model.getBoardContents(fromY, toX);
                boolean lastMoveDoublePawnMove = model.isLastMoveDoublePawnMove(toX, fromY);
                if (adjacentPawn == opponentPawn && lastMoveDoublePawnMove) {
                    return 3; // En passant
                }
            }
        } else if (player == 0) { // Black Pawn

            if (toY == 7 && ((fromX == toX && toY - fromY == 1 && piece == 0) || (Math.abs(fromX - toX) == 1 && toY - fromY == 1 && piece > 0 && piece <= 6))) {
                return 2; //promotion
            }
            if (fromX == toX && toY - fromY == 1 && piece == 0) {
                return 1; // Move forward
            }
            if (fromX == toX && fromY == 1 && toY - fromY == 2 && piece == 0) {
                return 1; // Double move forward
            }
            if (Math.abs(fromX - toX) == 1 && toY - fromY == 1 && piece > 0 && piece <= 6) {
                return 1; // Capture
            }

            if (fromY == 4 && toY == 5 && Math.abs(fromX - toX) == 1 && piece == 0) {
                int adjacentPawn = model.getBoardContents(fromY, toX);
                boolean lastMoveDoublePawnMove = model.isLastMoveDoublePawnMove(toX, fromY);
                if (adjacentPawn == opponentPawn && lastMoveDoublePawnMove) {
                    return 3; // En passant
                }
            }
        }
        return 0;
    }
}

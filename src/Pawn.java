public class Pawn extends Piece {

    public Pawn(int x, int y, int player) {
        super(x, y, player);
    }





    //check pawn moves are valid
    //NEED TO CODE EN PESSANT
    @Override
    public boolean isValidMove(int fromY, int fromX, int toY, int toX, Model model) {
        int piece = model.getBoardContents(toY, toX);

        if (player == 1) { // White Pawn
            if (fromX == toX && fromY - toY == 1 && piece == 0) {
                return true; // Move forward
            }
            if (fromX == toX && fromY == 6 && fromY - toY == 2 && piece == 0 && model.getBoardContents(toY+1, toX) == 0) {
                return true; // Double move forward
            }
            if (Math.abs(fromX - toX) == 1 && fromY - toY == 1 && piece > 6) {
                return true; // Capture
            }
        } else if (player == 0) { // Black Pawn
            if (fromX == toX && toY - fromY == 1 && piece == 0) {
                return true; // Move forward
            }
            if (fromX == toX && fromY == 1 && toY - fromY == 2 && piece == 0) {
                return true; // Double move forward
            }
            if (Math.abs(fromX - toX) == 1 && toY - fromY == 1 && piece > 0 && piece <= 6) {
                return true; // Capture
            }
        }
        return false;
    }
}

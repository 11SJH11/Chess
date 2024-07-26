public class King extends Piece {
    public King(int x, int y, int player) {
        super(x, y, player);
    }

    public boolean isValidMove(int fromX, int fromY, int toX, int toY, Model model) {
        int piece = model.getBoardContents(toX, toY);
        
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        // King can move one square in any direction
        if (deltaX > 1 || deltaY > 1) {
            return false;
        }
        
        if (piece != 0 && ((piece <= 6 && player == 1) || (piece >= 7 && player == 0))) {
            return false;
        }
        return true;
    }
}
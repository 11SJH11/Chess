public class Knight extends Piece {
    public Knight(int x, int y, int player) {
        super(x, y, player);
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY, Model model) {
        int piece = model.getBoardContents(toX, toY);
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        // Check if the move is an L-shape
        if (!((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))) {
            return false;
        }

        
        if (piece != 0 && ((piece <= 6 && player == 1) || (piece >= 7 && player == 0))) {
            return false;
        }
        return true;

       
    }
}
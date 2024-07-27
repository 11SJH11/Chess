public class Queen extends Piece {
    public Queen(int x, int y, int player) {
        super(x, y, player);
    }

    @Override
    public int isValidMove(int fromX, int fromY, int toX, int toY, Model model) {
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if (deltaX == deltaY || fromX == toX || fromY == toY) {
            // Check for obstacles in the path
            if (!isPathClear(fromX, fromY, toX, toY, model)) {
                return 0;
            }
            // Check if the destination is occupied by the same player's piece
            int destinationPiece = model.getBoardContents(toX, toY);
            if (destinationPiece != 0 && ((destinationPiece <= 6 && player == 1) || (destinationPiece >= 7 && player == 0))) {
                return 0;
            }
            return 1;
        }
        return 0;
    }

    private boolean isPathClear(int fromX, int fromY, int toX, int toY, Model model) {
        int deltaX = Integer.signum(toX - fromX);
        int deltaY = Integer.signum(toY - fromY);
        
        int x = fromX + deltaX;
        int y = fromY + deltaY;
        
        while (x != toX || y != toY) {
            if (model.getBoardContents(x, y) != 0) {
                return false;
            }
            x += deltaX;
            y += deltaY;
        }
        
        return true;
    }
}
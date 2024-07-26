public class Rook extends Piece {
    public Rook(int x, int y, int player) {
        super(x, y, player);
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY, Model model) {
        int piece = model.getBoardContents(toX, toY);
        
        // Check if the move is in a straight line
        if (fromX != toX && fromY != toY) {
            return false;
        }

        //if horizontal check nothing between fromY and toY
        //else vertical, check nothing between fromX and toX

        if (fromY == toY) {
            int step = fromY > toY ? 1 : -1;
            for(int y = fromY; y != toY; y += step){
                if (model.getBoardContents(toY, y) > 0) {
                    return false;
                }
            }
            
        }
        else{
            int step = fromX > toX ? 1 : -1;
            for(int x = fromX; x != toX; x += step){
                if (model.getBoardContents(toX, x) > 0) {
                    return false;
                }
            }
        }

        if (piece != 0 && ((piece <= 6 && player == 1) || (piece >= 7 && player == 0))) {
            return false;
        }

        return true;

       
    }
}

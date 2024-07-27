public abstract class Piece {
    protected int x;
    protected int y;
    protected int player; // 1 for white, 0 for black

    public Piece(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public abstract int isValidMove(int fromX, int fromY, int toX, int toY, Model model);
}
public class Model
{
	int [][] boardContents;
	int width;
	int height;
	int player;
	boolean finished;
	View view;
	Controller controller;
	private int lastMoveFromX = -1;
    private int lastMoveFromY = -1;
    private int lastMoveToX = -1;
    private int lastMoveToY = -1;
    private int lastMovePiece = -1;

	
	public Model() {
    }
	
	public void initialise(int width, int height, View view, Controller controller)
	{
		this.width = width;
		this.height = width;
		this.view = view;
		this.controller = controller;
		boardContents = new int[width][height];
	}

	
	public void clear(int value)
	{
		for ( int x = 0 ; x < width ; x++ )
			for ( int y = 0 ; y < height ; y++ )
				boardContents[x][y] = value;
	}

	
	public int getBoardWidth()
	{
		return width;
	}

	
	public int getBoardHeight()
	{
		return height;
	}

	
	public int getBoardContents(int x, int y)
	{
		return boardContents[x][y];
	}

	
	public void setBoardContents(int x, int y, int value)
	{
		boardContents[x][y] = value;
	}

	
	public void setPlayer(int player) // 0-black, 1-white
	{
		this.player = player;
	}

	
	public int getPlayer() // 0-black, 1-white
	{
		return player;
	}

	
	public boolean hasFinished()
	{
		return finished;
	}

	
	public void setFinished(boolean finished)
	{
		this.finished = finished;
	}

	public void setLastMove(int fromX, int fromY, int toX, int toY, int piece) {
        this.lastMoveFromX = fromX;
        this.lastMoveFromY = fromY;
        this.lastMoveToX = toX;
        this.lastMoveToY = toY;
        this.lastMovePiece = piece;
    }

    public boolean isLastMoveDoublePawnMove(int x, int y) {
        return !(Math.abs(lastMoveToY - lastMoveFromY) == 2 && lastMoveToX == x && lastMoveToY == y &&
            (lastMovePiece == 1 || lastMovePiece == 7));
    }
}

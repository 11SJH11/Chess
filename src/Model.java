import java.util.Dictionary;
import java.util.Hashtable;

public class Model
{
	int [][] boardContents;
	int width;
	int height;
	int player;
	boolean finished;
	boolean whiteCastle;
	boolean blackCastle;
	View view;
	Controller controller;
	private int lastMoveFromRow = -1;
    private int lastMoveFromCol = -1;
    private int lastMoveToRow = -1;
    private int lastMoveToY = -1;
    private int lastMovePiece = -1; 
	
	Dictionary<String, Integer> whiteCaptured;
    Dictionary<String, Integer> blackCaptured;
	
	public Model() {
		whiteCaptured = new Hashtable<>();
        blackCaptured = new Hashtable<>();
        initialiseCapturedPieces(whiteCaptured);
        initialiseCapturedPieces(blackCaptured);
    }
	
	public void initialise(int width, int height, View view, Controller controller)
	{
		this.width = width;
		this.height = width;
		this.view = view;
		this.controller = controller;
		boardContents = new int[width][height];
	}



	private void initialiseCapturedPieces(Dictionary<String, Integer> captured) {
        captured.put("Pawn", 0);
        captured.put("Rook", 0);
        captured.put("Knight", 0);
        captured.put("Bishop", 0);
        captured.put("Queen", 0);
        captured.put("King", 0);
    }

	public Dictionary<String, Integer> getWhiteCaptured() {
        return whiteCaptured;
    }

    public Dictionary<String, Integer> getBlackCaptured() {
        return blackCaptured;
    }

	public void incrementCapturedPiece(boolean isWhite, String pieceName) {
        Dictionary<String, Integer> captured = isWhite ? blackCaptured : whiteCaptured;
        captured.put(pieceName, captured.get(pieceName) + 1);
    }
	public void decrementCapturedPiece(boolean isWhite, String pieceName) {
        Dictionary<String, Integer> captured = isWhite ? blackCaptured : whiteCaptured;
        captured.put(pieceName, captured.get(pieceName) - 1);
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

	
	public int getBoardContents(int row, int col)
	{
		return boardContents[row][col];
	}

	
	public void setBoardContents(int row, int col, int value)
	{
		boardContents[row][col] = value;
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

	public void setWhiteCastled(boolean whiteCastle){
		this.whiteCastle = whiteCastle;
	}

	public boolean hasWhiteCastled(){
		return whiteCastle;
	}

	public void setBlackCastled(boolean blackCastle){
		this.blackCastle = blackCastle;
	}

	public boolean hasBlackCastled(){
		return blackCastle;
	}


	public void setLastMove(int fromRow, int fromCol, int toRow, int toCol, int piece) {
        this.lastMoveFromRow = fromRow;
        this.lastMoveFromCol = fromCol;
        this.lastMoveToRow = toRow;
        this.lastMoveToY = toCol;
        this.lastMovePiece = piece;
    }

    public boolean isLastMoveDoublePawnMove(int x, int y) {
        return !(Math.abs(lastMoveToY - lastMoveFromCol) == 2 && lastMoveToRow == x && lastMoveToY == y &&
            (lastMovePiece == 1 || lastMovePiece == 7));
    }
}

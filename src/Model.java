public class Model
{
	int [][] boardContents;
	int width;
	int height;
	int player;
	boolean finished;
	View view;
	Controller controller;
	
	
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
}

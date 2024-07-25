import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Enumeration;

public class Controller {

    Model model;
    View view;
    Dictionary<String, Integer> pieces;
    



    public Controller(){

    }

    public void initialise(Model model, View view){
        this.model = model;
        this.view = view;
        
        pieces = new Hashtable<>();
        pieces.put("Empty", 0);
        pieces.put("WhitePawn", 1);
        pieces.put("WhiteRook", 2);
        pieces.put("WhiteKnight", 3);
        pieces.put("WhiteBishop", 4);
        pieces.put("WhiteQueen", 5);
        pieces.put("WhiteKing", 6);

        pieces.put("BlackPawn", 7);
        pieces.put("BlackRook", 8);
        pieces.put("BlackKnight", 9);
        pieces.put("BlackBishop", 10);
        pieces.put("BlackQueen", 11);
        pieces.put("BlackKing", 12);

    }


    public void startup() {
        //set pieces+messages

        int width = model.getBoardWidth();
		int height = model.getBoardHeight();
		for ( int x = 0 ; x < width ; x++ )
			for ( int y = 0 ; y < height ; y++ )
				model.setBoardContents(x, y, 0);

        starting_pieces();

        model.setPlayer(1);
        
        System.out.println(model.hasFinished());
    }

    
    public void squareSelected(int player, int x, int y) {
        System.out.println(model.getBoardContents(x, y));
    }



    public void starting_pieces(){
        int i, j;
        model.setBoardContents(0, 0, pieces.get("BlackRook"));
        model.setBoardContents(0, 1, pieces.get("BlackKnight"));
        model.setBoardContents(0, 2, pieces.get("BlackBishop"));
        model.setBoardContents(0, 3, pieces.get("BlackQueen"));
        model.setBoardContents(0, 4, pieces.get("BlackKing"));
        model.setBoardContents(0, 5, pieces.get("BlackBishop"));
        model.setBoardContents(0, 6, pieces.get("BlackKnight"));
        model.setBoardContents(0, 7, pieces.get("BlackRook"));

        model.setBoardContents(7, 0, pieces.get("WhiteRook"));
        model.setBoardContents(7, 1, pieces.get("WhiteKnight"));
        model.setBoardContents(7, 2, pieces.get("WhiteBishop"));
        model.setBoardContents(7, 3, pieces.get("WhiteQueen"));
        model.setBoardContents(7, 4, pieces.get("WhiteKing"));
        model.setBoardContents(7, 5, pieces.get("WhiteBishop"));
        model.setBoardContents(7, 6, pieces.get("WhiteKnight"));
        model.setBoardContents(7, 7, pieces.get("WhiteRook"));

        for(i=0; i<8; i++){
            model.setBoardContents(1, i, pieces.get("BlackPawn"));
            model.setBoardContents(6, i, pieces.get("WhitePawn"));
        }
        for(i=2; i<6; i++){
            for(j=0; j<7; j++){
                model.setBoardContents(i, j, pieces.get("Empty"));
            }
        }
    }

}

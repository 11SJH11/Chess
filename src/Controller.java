import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Controller {

    Model model;
    View view;
    Dictionary<String, Integer> pieces;
    public int click = 0;

    private int previousRow = -1;
    private int previousCol = -1;

    public Controller() {}

    public void initialise(Model model, View view) {
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
        int width = model.getBoardWidth();
        int height = model.getBoardHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                model.setBoardContents(x, y, 0);
            }
        }

        starting_pieces();
        view.clearPanels();
        resetCaptured();
        model.setFinished(false);
        model.setPlayer(1);
        model.setBlackCastled(false);
        model.setWhiteCastled(false);
        view.feedback_to_user("WHITE PLAYER- select piece to move");
        System.out.println("Startup");
    }

    public void squareSelected(int player, int row, int col) {
        
        System.out.println("Row: " + row + " Col: " + col + " Value: " + model.getBoardContents(row, col));
        if (model.hasFinished()) {
            return;
        }

        if (click == 0) {
            if (correctPlayerMove(model.getPlayer(), row, col)) {
                previousRow = row;
                previousCol = col;
                view.feedback_to_user("Select location to move to");
                click = 1;
            } else {
                view.feedback_to_user(model.getPlayer() == 1? "White player-select piece":"Black player-select piece");
            }
        } else {
            if (previousRow != -1 && previousCol != -1) {
                secondsquare(model.getPlayer(), previousRow, previousCol, row, col);
            }
            click = 0;
            previousRow = -1;
            previousCol = -1;
        }
    }

    public void secondsquare(int player, int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece = createPiece(fromRow, fromCol, model.getBoardContents(fromRow, fromCol));
        int validMove = piece.isValidMove(fromRow, fromCol, toRow, toCol, model);
        if (piece != null && validMove == 1) {
            movePiece(fromRow, fromCol, toRow, toCol);
            view.feedback_to_user("Move successful");
            model.setPlayer(player == 1 ? 0 : 1); // Switch turns
            view.feedback_to_user(player == 1 ? "BLACK PLAYER - select piece to move" : "WHITE PLAYER - select piece to move");
        } else if (validMove == 2) {
            view.feedback_to_user("Move successful-Promoted to Queen");
            promotePawn(fromRow, fromCol, toRow, toCol, player);
            model.setPlayer(player == 1 ? 0 : 1);
        }
        else if (validMove == 3) {
            view.feedback_to_user("Move successful-En passant");
            enPassant(fromRow, fromCol, toRow, toCol, player);
            model.setPlayer(player == 1 ? 0 : 1);
        }
        else if (validMove == -1) {
            view.feedback_to_user("Move successful-Queen Castle");
            castleLeft(toRow, toCol, player);
            model.setPlayer(player == 1 ? 0 : 1);
            
        } 
        else if (validMove == -2) {
            view.feedback_to_user("Move successful-King Castle");
            castleRight(toRow, toCol, player);
            model.setPlayer(player == 1 ? 0 : 1);
            
        } 
        else {
            view.feedback_to_user(player == 1? "Invalid move-White player select piece":"Invalid move-Black player select piece");
        }
                
        displayCapturedPieces(player);
        view.update();
        gameEnd();
    }

    private Piece createPiece(int x, int y, int pieceCode) {
        switch (pieceCode) {
            case 1:
                return new Pawn(x, y, 1);
            case 2:
                return new Rook(x, y, 1);
            case 3:
                return new Knight(x, y, 1);   
            case 4:
                return new Bishop(x, y, 1);
            case 5:
                return new Queen(x, y, 1);
            case 6:
                return new King(x, y, 1);
            case 7:
                return new Pawn(x, y, 0);
            case 8:
                return new Rook(x, y, 0);
            case 9:
                return new Knight(x, y, 0);
            case 10:
                return new Bishop(x, y, 0);
            case 11:
                return new Queen(x, y, 0);
            case 12:
                return new King(x, y, 0);
            default:
                return null;
        }
    }

    private void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        
        int piece = model.getBoardContents(fromRow, fromCol);

        int capturedPiece = model.getBoardContents(toRow, toCol);

        // Check if a piece is being captured
        if (capturedPiece != pieces.get("Empty")) {
            boolean isWhiteCaptured = capturedPiece >= 7 && capturedPiece <= 12;
            String pieceName = getPieceName(capturedPiece);
            model.incrementCapturedPiece(isWhiteCaptured, pieceName);
        }

        model.setBoardContents(fromRow, fromCol, 0);
        model.setBoardContents(toRow, toCol, piece);

    }

    private String getPieceName(int pieceCode) {
        switch (pieceCode) {
            case 1:
            case 7:
                return "Pawn";
            case 2:
            case 8:
                return "Rook";
            case 3:
            case 9:
                return "Knight";
            case 4:
            case 10:
                return "Bishop";
            case 5:
            case 11:
                return "Queen";
            case 6:
            case 12:
                return "King";
            default:
                return "Unknown";
        }
    }

    private void promotePawn(int fromRow, int fromCol, int toRow, int toCol, int player) {
        int capturedPiece = model.getBoardContents(toRow, toCol);

        // Check if a piece is being captured
        if (capturedPiece != pieces.get("Empty")) {
            boolean isWhiteCaptured = capturedPiece >= 7 && capturedPiece <= 12;
            String pieceName = getPieceName(capturedPiece);
            model.incrementCapturedPiece(isWhiteCaptured, pieceName);
        }
        model.decrementCapturedPiece(player==1?false:true, getPieceName(5));
        model.setBoardContents(fromRow, fromCol, 0);
        model.setBoardContents(toRow, toCol, player == 1 ? pieces.get("WhiteQueen") : pieces.get("BlackQueen"));
    }
    private void enPassant(int fromRow, int fromCol, int toRow, int toCol, int player) {
        int opponentPawnY = (player == 1) ? toRow + 1 : toRow - 1;

        int capturedPiece = model.getBoardContents(player==1?toRow+1:toRow-1, toCol);

        // Check if a piece is being captured
        if (capturedPiece != pieces.get("Empty")) {
            boolean isWhiteCaptured = capturedPiece >= 7 && capturedPiece <= 12;
            String pieceName = getPieceName(capturedPiece);
            model.incrementCapturedPiece(isWhiteCaptured, pieceName);
        }
        model.setBoardContents(fromRow, fromCol, 0);
        model.setBoardContents(toRow, toCol, player == 1 ? pieces.get("WhitePawn") : pieces.get("BlackPawn"));
        model.setBoardContents(opponentPawnY, toCol, 0);
    }


    private void castleLeft(int toRow, int toCol, int player){
        model.setBoardContents(player == 1 ? 7 : 0, 0, 0);
        model.setBoardContents(player == 1 ? 7 : 0, 4, 0);
        model.setBoardContents(toRow, toCol, player == 1 ? pieces.get("WhiteKing") : pieces.get("BlackKing"));
        model.setBoardContents(toRow, toCol+1, player == 1 ? pieces.get("WhiteRook") : pieces.get("BlackRook"));
        
        if(player == 1){
            model.setWhiteCastled(true);
        }
        else{
            model.setBlackCastled(true);
        }

    }

    private void castleRight(int toRow, int toCol, int player){
        model.setBoardContents(player == 1 ? 7 : 0, 7, 0);
        model.setBoardContents(player == 1 ? 7 : 0, 4, 0);
        model.setBoardContents(toRow, toCol, player == 1 ? pieces.get("WhiteKing") : pieces.get("BlackKing"));
        model.setBoardContents(toRow, toCol-1, player == 1 ? pieces.get("WhiteRook") : pieces.get("BlackRook"));  

        if(player == 1){
            model.setWhiteCastled(true);
        }
        else{
            model.setBlackCastled(true);
        }
    }


    private boolean correctPlayerMove(int player, int x, int y){
        int piece = model.getBoardContents(x, y);
        if (player == 1 && piece >= 1 && piece <= 6) {
            return true; // white's turn
        } else if (player == 0 && piece >= 7 && piece <= 12) {
            return true; // black's turn
        }
        return false;
    }

    public void starting_pieces() {
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

        for (i = 0; i < 8; i++) {
            model.setBoardContents(1, i, pieces.get("BlackPawn"));
            model.setBoardContents(6, i, pieces.get("WhitePawn"));
        }
        for (i = 2; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                model.setBoardContents(i, j, pieces.get("Empty"));
            }
        }
        view.update();
    }
    int count = 0;
    public void displayCapturedPieces(int player) {
        Dictionary<String, Integer> whiteCaptured = model.getWhiteCaptured();
        Dictionary<String, Integer> blackCaptured = model.getBlackCaptured();
           
        
            if (count == 6) {
                view.clearPanels();
                count = 0;
            }
            
            for (Enumeration<String> keys = whiteCaptured.keys(); keys.hasMoreElements();) {
                String key = keys.nextElement();
                view.addPieceMessage(key, whiteCaptured.get(key), 0);
                view.addPieceMessage(key, blackCaptured.get(key), 1);
                count++; 
            }   
            
    }

    public void resetCaptured(){
        Dictionary<String, Integer> whiteCaptured = model.getWhiteCaptured();
        Dictionary<String, Integer> blackCaptured = model.getBlackCaptured();

        for (Enumeration<String> keys = whiteCaptured.keys(); keys.hasMoreElements();) {
            String key = keys.nextElement();
            whiteCaptured.put(key, 0);
            blackCaptured.put(key, 0);
        }

    }
    

    public void gameEnd(){
        
        System.out.println(model.getWhiteCaptured().get("King"));
        if (model.getWhiteCaptured().get("King") > 0) {
            model.setFinished(true);    
            view.feedback_to_user("Black wins");   
        }
        if(model.getBlackCaptured().get("King") > 0) {
            model.setFinished(true);       
            view.feedback_to_user("White wins");
        }
                
    }

}

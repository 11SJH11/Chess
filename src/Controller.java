import java.util.Dictionary;
import java.util.Hashtable;

public class Controller {

    Model model;
    View view;
    Dictionary<String, Integer> pieces;
    public int click = 0;

    private int previousX = -1;
    private int previousY = -1;

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
        model.setPlayer(1);
        view.feedback_to_user("WHITE PLAYER- select piece to move");
        System.out.println("Startup");
    }

    public void squareSelected(int player, int x, int y) {
        
        System.out.println("x: " + x + " y: " + y + " Value: " + model.getBoardContents(x, y));
        if (click == 0) {
            if (correctPlayerMove(model.getPlayer(), x, y)) {
                previousX = x;
                previousY = y;
                view.feedback_to_user("Select location to move to");
                click = 1;
            } else {
                view.feedback_to_user(model.getPlayer() == 1? "White player-select piece":"Black player-select piece");
            }
        } else {
            if (previousX != -1 && previousY != -1) {
                secondsquare(model.getPlayer(), previousX, previousY, x, y);
            }
            click = 0;
            previousX = -1;
            previousY = -1;
        }
    }

    public void secondsquare(int player, int fromX, int fromY, int toX, int toY) {
        Piece piece = createPiece(fromX, fromY, model.getBoardContents(fromX, fromY));
        int validMove = piece.isValidMove(fromX, fromY, toX, toY, model);
        if (piece != null && validMove == 1) {
            movePiece(fromX, fromY, toX, toY);
            view.feedback_to_user("Move successful");
            model.setPlayer(player == 1 ? 0 : 1); // Switch turns
            view.feedback_to_user(player == 1 ? "BLACK PLAYER - select piece to move" : "WHITE PLAYER - select piece to move");
        } else if (validMove == 2) {
            view.feedback_to_user("Move successful-Promoted to Queen");
            promotePawn(fromX, fromY, toX, toY, player);
            model.setPlayer(player == 1 ? 0 : 1);
        }
        else if (validMove == 3) {
            view.feedback_to_user("Move successful-En passant");
            enPassant(fromX, fromY, toX, toY, player);
            model.setPlayer(player == 1 ? 0 : 1);
        } else {
            System.out.println("IF4");
            view.feedback_to_user(player == 1? "Invalid move-White player select piece":"Invalid move-Black player select piece");
        }
        view.update();
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

    private void movePiece(int fromX, int fromY, int toX, int toY) {
        int piece = model.getBoardContents(fromX, fromY);
        model.setBoardContents(fromX, fromY, 0);
        model.setBoardContents(toX, toY, piece);

    }

    private void promotePawn(int fromX, int fromY, int toX, int toY, int player) {
        model.setBoardContents(fromX, fromY, 0);
        model.setBoardContents(toX, toY, player == 1 ? pieces.get("WhiteQueen") : pieces.get("BlackQueen"));
    }
    private void enPassant(int fromX, int fromY, int toX, int toY, int player) {
        int opponentPawnY = (player == 1) ? toX + 1 : toX - 1;
        model.setBoardContents(fromX, fromY, 0);
        model.setBoardContents(toX, toY, player == 1 ? pieces.get("WhitePawn") : pieces.get("BlackPawn"));
        model.setBoardContents(opponentPawnY, toY, 0);
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
}

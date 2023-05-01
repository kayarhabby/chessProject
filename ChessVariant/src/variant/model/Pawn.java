package variant.model;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(Color color, int row, int col,boolean isWhiteTurn) {
        super("Pawn", color, row, col, isWhiteTurn);
    }

    public Pawn(ChessPiece piece) {
        super(piece.getPieceName(), piece.getColor(), piece.getRow(), piece.getCol(), piece.getWhiteTurn());
    }

    public void promotePawn(Pawn pawn, int row, int col, String pieceType, ChessPiece[][] board) {
        Color color = pawn.getColor();
        ChessPiece newPiece;
        switch (pieceType) {
            case "Queen":
                newPiece = new Queen(color, row, col,pawn.getWhiteTurn());
                break;
            case "Rook":
                newPiece = new Rook(color, row, col,pawn.getWhiteTurn());
                break;
            case "Bishop":
                newPiece = new Bishop(color, row, col,pawn.getWhiteTurn());
                break;
            case "Knight":
                newPiece = new Knight(color, row, col,pawn.getWhiteTurn());
                break;
            case "Imperatrice":
                newPiece = new Imperatrice(color,row,col,pawn.getWhiteTurn());
                break;
            case "Princesse":
                newPiece = new Princesse(color,row,col,pawn.getWhiteTurn());
                break;
            case "Noctambule":
                newPiece = new Noctambule(color,row,col,pawn.getWhiteTurn());
                break;
            case "Sauterelle":
                newPiece = new Sauterelle(color,row,col,pawn.getWhiteTurn());
                break;
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
        board[row][col] = newPiece;
    }

    public ArrayList<int[]> PossiblesMoves(int startXCol, int startYRow, ChessPiece[][] board) {
        ArrayList<int[]> moves = new ArrayList<>();

        if (this.getWhiteTurn()) {
            moves = possiblesMovesWhiteTurnTrue(startXCol, startYRow, board, moves);
        } else {
            moves = possiblesMovesWhiteTurnFalse(startXCol, startYRow, board, moves);
        }

        return moves;
    }

    private void descent(int startXCol, int startYRow, ChessPiece[][] board, ArrayList<int[]> moves, Boolean isWhite) {
        if (startXCol < 7 && board[startXCol + 1][startYRow] == null) {
            moves.add(new int[]{startXCol + 1, startYRow});
        }
        // Check two steps forward
        if (startXCol == 1 && board[startXCol + 1][startYRow] == null && board[startXCol + 2][startYRow] == null) {
            moves.add(new int[]{startXCol + 2, startYRow});
        }
        // Check diagonal captures
        if (startXCol < 8 && startYRow > 0 && board[startXCol + 1][startYRow - 1] != null && board[startXCol + 1][startYRow - 1].isWhite() == isWhite) {
            moves.add(new int[]{startXCol + 1, startYRow - 1});
        }
        if (startXCol < 8 && startYRow < 11 && board[startXCol + 1][startYRow + 1] != null && board[startXCol + 1][startYRow + 1].isWhite() == isWhite) {
            moves.add(new int[]{startXCol + 1, startYRow + 1});
        }

    }

    private void climb(int startXCol, int startYRow, ChessPiece[][] board, ArrayList<int[]> moves, Boolean isWhite) {
        if (startXCol > 0 && board[startXCol - 1][startYRow] == null) {
            moves.add(new int[]{startXCol - 1, startYRow});
        }
        // Check two steps forward
        if (startXCol == 6 && board[startXCol - 1][startYRow] == null && board[startXCol - 2][startYRow] == null) {
            moves.add(new int[]{startXCol - 2, startYRow});
        }
        // Check diagonal captures
        if (startXCol > 0 && startYRow > 0 && board[startXCol - 1][startYRow - 1] != null && board[startXCol - 1][startYRow - 1].isWhite() == isWhite) {
            moves.add(new int[]{startXCol - 1, startYRow - 1});
        }
        if (startXCol > 0 && startYRow < 11 && board[startXCol - 1][startYRow + 1] != null && board[startXCol - 1][startYRow + 1].isWhite() == isWhite) {
            moves.add(new int[]{startXCol - 1, startYRow + 1});
        }

    }

    public ArrayList<int[]> possiblesMovesWhiteTurnFalse(int startXCol, int startYRow, ChessPiece[][] board,
                                                         ArrayList<int[]> moves) {

        if (this.isWhite()) {  // Descente blanche.
            descent(startXCol, startYRow, board, moves, false);
        } else { // Montée Noir.
            // Check one step forward
            climb(startXCol, startYRow, board, moves, true);
        }
        return moves;
    }


    public ArrayList<int[]> possiblesMovesWhiteTurnTrue(int startXCol, int startYRow, ChessPiece[][] board,
                                                        ArrayList<int[]> moves) {
        if (!this.isWhite()) {  // Descente Noire
            descent(startXCol, startYRow, board, moves, true);
        } else {  // Montee blanche.
            climb(startXCol, startYRow, board, moves, false);
        }
        return moves;
    }

}
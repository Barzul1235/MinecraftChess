package me.barzul.chess.backend;

import me.barzul.chess.backend.pieces.*;

public class Game {

    private final Piece[][] field;

    public Game() {
        // Generate empty chess field
        field = new Piece[][]{
                {new Rook(0, 7, false, this), new Knight(1, 7, false, this), new Bishop(2, 7, false, this), new Queen(3, 7, false, this), new King(4, 7, false, this), new Bishop(5, 7, false, this), new Knight(6, 7, false, this), new Rook(7, 7, false, this)},
                {new Pawn(0, 6, false, this), new Pawn(1, 6, false, this), new Pawn(2, 6, false, this), new Pawn(3, 6, false, this), new Pawn(4, 6, false, this), new Pawn(5, 6, false, this), new Pawn(6, 6, false, this), new Pawn(7, 6, false, this)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(0, 1, true, this), new Pawn(1, 1, true, this), new Pawn(2, 1, true, this), new Pawn(3, 1, true, this), new Pawn(4, 1, true, this), new Pawn(5, 1, true, this), new Pawn(6, 1, true, this), new Pawn(7, 1, true, this)},
                {new Rook(0, 0, true, this), new Knight(1, 0, true, this), new Bishop(2, 0, true, this), new Queen(3, 0, true, this), new King(4, 0, true, this), new Bishop(5, 0, true, this), new Knight(6, 0, true, this), new Rook(7, 0, true, this)}
        };
    }

    // TODO
    public boolean hasGameEnded() {
        return true;
    }

    public boolean isEmpty(int[] destinationCoords) {
        return field[destinationCoords[0]][destinationCoords[1]] == null;
    }

    public boolean canBeAttacked(int[] destinationCoords, boolean colour) {
        try {

            return field[destinationCoords[0]][destinationCoords[1]] != null && field[destinationCoords[0]][destinationCoords[1]].colour != colour;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFieldSave(int[] destinationCoords, boolean colour) {
        for (Piece[] row : field) {
            for (Piece piece : row) {
                if (piece != null && piece.colour != colour) {
                    if (piece.isMovePossible(destinationCoords) && canBeAttacked(destinationCoords, !colour)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isInField(int[] destinationCoords) {
        return (destinationCoords[0] < 8 && destinationCoords[0] >= 0 && destinationCoords[1] < 8 && destinationCoords[1] >= 0);
    }

}

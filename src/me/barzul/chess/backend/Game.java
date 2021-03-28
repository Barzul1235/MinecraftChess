package me.barzul.chess.backend;

import me.barzul.chess.backend.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public Piece[][] field;
    public List<Piece> defeatedPieces = new ArrayList<>();

    public Game() {
        // Leeres Spielfeld erstellen
        field = new Piece[][]{
                {new Rook(0, 0, false, this), new Knight(1, 0, false, this), new Bishop(2, 0, false, this), new Queen(3, 0, false, this), new King(4, 0, false, this), new Bishop(5, 0, false, this), new Knight(6, 0, false, this), new Rook(7, 0, false, this)},
                {new Pawn(0, 1, false, this), new Pawn(1, 1, false, this), new Pawn(2, 1, false, this), new Pawn(3, 1, false, this), new Pawn(4, 1, false, this), new Pawn(5, 1, false, this), new Pawn(6, 1, false, this), new Pawn(7, 1, false, this)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn(0, 6, true, this), new Pawn(1, 6, true, this), new Pawn(2, 6, true, this), new Pawn(3, 6, true, this), new Pawn(4, 6, true, this), new Pawn(5, 6, true, this), new Pawn(6, 6, true, this), new Pawn(7, 6, true, this)},
                {new Rook(0, 7, true, this), new Knight(1, 7, true, this), new Bishop(2, 7, true, this), new Queen(3, 7, true, this), new King(4, 7, true, this), new Bishop(5, 7, true, this), new Knight(6, 7, true, this), new Rook(7, 7, true, this)}
        };
    }

    // Sie sehen hier: Umständlicher Code 3001... Naja, hatte keine Lust Variablen zu nutzen ;)
    public boolean movePiece(int[] startCoords, int[] destinationCoords, boolean forceMove) {
        if(field[startCoords[1]][startCoords[0]].isMovePossible(destinationCoords) || forceMove) {
            field[startCoords[1]][startCoords[0]].moveTo(destinationCoords[1], destinationCoords[0]);

            if(field[destinationCoords[1]][destinationCoords[0]] != null) {
                defeatedPieces.add(field[destinationCoords[0]][destinationCoords[1]]);
            }

            field[destinationCoords[1]][destinationCoords[0]] = field[startCoords[1]][startCoords[0]];
            field[startCoords[1]][startCoords[0]] = null;

            return true;
        }
        return false;
    }

    // TODO
    public boolean hasGameEnded() {
        return true;
    }

    public boolean isEmpty(int[] destinationCoords) {
        return field[destinationCoords[1]][destinationCoords[0]] == null;
    }

    public boolean canBeAttacked(int[] destinationCoords, boolean colour) {
        try {

            return field[destinationCoords[1]][destinationCoords[0]] != null && field[destinationCoords[1]][destinationCoords[0]].colour != colour;

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

    public boolean doShortCasteling(boolean colour) {
        if(colour) {
            if(field[7][4] instanceof King && field[7][7] instanceof Rook) {
                King king = (King) field[7][4];
                Rook rook = (Rook) field[7][7];
                if(!king.hasMovedBefore && !rook.hasMovedBefore){
                    if(field[7][5] == null && field[7][6] == null && isFieldSave(new int[]{6, 7}, true)) {
                        movePiece(new int[]{4, 7}, new int[]{6, 7}, true);
                        movePiece(new int[]{7, 7}, new int[]{5, 7}, true);
                        return true;
                    }
                }
            }
        } else {
            if(field[4][7] instanceof King && field[7][7] instanceof Rook) {
                King king = (King) field[4][7];
                Rook rook = (Rook) field[7][7];
                if(!king.hasMovedBefore && !rook.hasMovedBefore){
                    if(field[5][7] == null && field[6][7] == null && isFieldSave(new int[]{6, 7}, false)) {
                        movePiece(new int[]{4, 7}, new int[]{6, 7}, true);
                        movePiece(new int[]{7, 7}, new int[]{5, 7}, true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean doLongCasteling(boolean colour) {
        if(colour) {
            if(field[7][4] instanceof King && field[7][0] instanceof Rook) {
                King king = (King) field[7][4];
                Rook rook = (Rook) field[7][0];
                if(!king.hasMovedBefore && !rook.hasMovedBefore){
                    if(field[7][5] == null && field[7][6] == null && isFieldSave(new int[]{6, 7}, true)) {
                        movePiece(new int[]{4, 7}, new int[]{2, 7}, true);
                        movePiece(new int[]{7, 0}, new int[]{3, 7}, true);
                        return true;
                    }
                }
            }
        } else {
            if(field[4][7] instanceof King && field[7][0] instanceof Rook) {
                King king = (King) field[4][7];
                Rook rook = (Rook) field[7][0];
                if(!king.hasMovedBefore && !rook.hasMovedBefore){
                    if(field[5][7] == null && field[6][7] == null && isFieldSave(new int[]{6, 7}, false)) {
                        movePiece(new int[]{4, 7}, new int[]{2, 7}, true);
                        movePiece(new int[]{7, 0}, new int[]{3, 7}, true);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

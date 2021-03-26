package me.barzul.chess.backend;

import me.barzul.chess.backend.pieces.Piece;

public class Game {

    private Piece[][] field;

    public Game() {
        // Generate empty chess field
        // TODO s. oben
    }

    // TODO
    public boolean hasGameEnded(){
        return true;
    }

    public boolean isEmpty(int[] destinationCoords) {
        if(field[destinationCoords[0]][destinationCoords[1]] == null) {
            return true;
        }
        return false;
    }

    public boolean canBeAttacked(int[] destinationCoords, boolean colour) {

        try {

            if (field[destinationCoords[0]][destinationCoords[1]] != null && field[destinationCoords[0]][destinationCoords[1]].colour != colour) {
                return true;
            }

            return false;

        } catch(Exception e) {
            return false;
        }

    }

    public boolean isInField(int[] destinationCoords) {

        return (destinationCoords[0] < 8 && destinationCoords[0] >= 0 && destinationCoords[1] < 8 && destinationCoords[1] >= 0);

    }

}

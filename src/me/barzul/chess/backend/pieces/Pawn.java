package me.barzul.chess.backend.pieces;

import me.barzul.chess.backend.Game;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public boolean hasMovedBefore = false;

    public Pawn(int x, int y, boolean colour, Game game) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    @Override
    public boolean isMovePossible(int[] destinationCoords) {
        return getPossibleMoves().contains(destinationCoords);
    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();

        int direction = colour ? 1 : -1;

        if(colour) {
            if(game.isEmpty(new int[]{x, y + direction}) && game.isInField(new int[]{x, y + direction}))
                moves.add(new int[]{x, y + direction});
            if(!hasMovedBefore) {
                if(game.isEmpty(new int[]{x, y + (2*direction)}) && game.isEmpty(new int[]{x, y + direction})
                        && game.isInField(new int[]{x, y + (2*direction)}) && game.isInField(new int[]{x, y + direction}))
                    moves.add(new int[]{x, y + (2*direction)});
            }
            if (game.canBeAttacked(new int[]{x + 1, y + direction}, colour)) {
                moves.add(new int[]{x + 1, y + direction});
            }
            if (game.canBeAttacked(new int[]{x - 1, y + direction}, colour)) {
                moves.add(new int[]{x - 1, y + direction});
            }
        }

        return moves;
    }

    @Override
    public boolean moveTo(int x, int y) {
        if(isMovePossible(new int[]{x, y})) {
            this.x = x;
            this.y = y;
            hasMovedBefore = true;
            return true;
        }
        return false;
    }
}

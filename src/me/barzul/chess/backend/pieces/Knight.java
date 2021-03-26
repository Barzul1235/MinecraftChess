package me.barzul.chess.backend.pieces;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    @Override
    public boolean isMovePossible(int[] destinationCoords) {

        return getPossibleMoves().contains(destinationCoords);

    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();

        int[][] positions = new int[][]{{x + 1, y + 2}, {x + 2, y + 1}, {x + 2, y - 1}, {x + 1, y - 2}, {x - 1, y - 2}, {x - 2, y + 1}, {x - 1, y + 2}};

        for(int [] position: positions) {
            if(game.isInField(position) && !game.canBeAttacked(position, !colour)) {
                moves.add(position);
            }
        }

        return moves;
    }

    @Override
    public boolean moveTo(int x, int y) {
        if(isMovePossible(new int[]{x, y})) {
            this.x = x;
            this.y = y;
            return true;
        }
        return false;
    }
}

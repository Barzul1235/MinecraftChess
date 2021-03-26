package me.barzul.chess.backend.pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    @Override
    public boolean isMovePossible(int[] destinationCoords) {

        return getPossibleMoves().contains(destinationCoords);

    }

    @Override
    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();

        int[][] offsets = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] offset: offsets) {
            int tempX = x;
            int tempY = y;
            while(true){
                tempX += offset[0];
                tempY += offset[1];
                if(game.isInField(new int[]{tempX, tempY}) && !game.canBeAttacked(new int[]{tempX, tempY}, !colour)) {
                    moves.add(new int[]{tempX, tempY});
                    if(game.canBeAttacked(new int[]{tempX, tempY}, colour)) {
                        break;
                    }
                } else {
                    break;
                }
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

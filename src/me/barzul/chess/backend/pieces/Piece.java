package me.barzul.chess.backend.pieces;

import me.barzul.chess.backend.Game;

import java.util.List;

public abstract class Piece {

    /*
    white = true
    black = false
     */
    public boolean colour = true;

    public int x = 1;
    public int y = 1;

    public Game game = new Game();


    public abstract boolean isMovePossible(int[] destinationCoords);
    public abstract List<int[]> getPossibleMoves();
    public abstract boolean moveTo(int x, int y);

}

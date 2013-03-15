package net.aparsons.praetorian.rota.model;

import java.util.Arrays;

public class Board {
        
    private Piece[] board = new Piece[9];
    
    private int playerPieceCount = 0;
    private int computerPieceCount = 0;
    
    public Board() {
        Arrays.fill(board, Piece.EMPTY);
    }
    
    public Board(String b) {
        for (int i = 0; i < b.length(); i++) {
            Piece p = Piece.getEnum(String.valueOf(b.charAt(i)));
            
            if (p.equals(Piece.COMPUTER)) {
                computerPieceCount++;
            } else if (p.equals(Piece.PLAYER)) {
                playerPieceCount++;
            }
            
            board[i] = p;
        }
    }
    
//    public void place(Piece p, int position) {
//        board[position - 1] = p;
//    }
//
//    public void move(int from, int to) {
//        board[to - 1] = board[from - 1];
//        board[from - 1] = Piece.EMPTY;
//    }
    
    public int getPlayerPieceCount() {
        return playerPieceCount;
    }

    public int getComputerPieceCount() {
        return computerPieceCount;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(board[0]).append(" ").append(board[1]).append(" ").append(board[2]).append("\r\n");
        sb.append(board[3]).append(" ").append(board[4]).append(" ").append(board[5]).append("\r\n");
        sb.append(board[6]).append(" ").append(board[7]).append(" ").append(board[8]);
        
        return sb.toString();
    }
    
}

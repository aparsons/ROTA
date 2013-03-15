package net.aparsons.praetorian.rota.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aparsons.praetorian.rota.http.API;
import net.aparsons.praetorian.rota.http.Client;
import net.aparsons.praetorian.rota.model.Board;
import net.aparsons.praetorian.rota.model.Response;
import org.apache.commons.lang.time.StopWatch;

public class Controller implements Runnable {
    
    private Client client;
    
    public Controller() {
        client = new Client();
    }
    
    public void run() {
        StopWatch watch = new StopWatch();

        Board board;
        
        watch.start();
        Response resp = client.request(API.start());
        
        while (resp.data.hash != null) {
            
            // Update board
            board = new Board(resp.data.board);
            
            // Print to screen
            System.out.println(board);
            System.out.println("Player Wins: " + resp.data.player_wins);
            System.out.println("Session Time: " + watch.toSplitString());
            
            // Action
            if (board.getPlayerPieceCount() >= 3) {
                move(board);
            } else {
                place(board);
            }
 
            // Sleep
            try {
                Thread.sleep(5000L); // 5 Seconds
            } catch (InterruptedException ie) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, ie.getMessage(), ie);
            }
            
            // Status
            resp = client.request(API.status());
            
            // Reset
            if (resp.data.computer_wins > 0) {
                watch.split();
                resp = client.request(API.start());
            }
            
        }
        watch.stop();
        
        System.out.println("Took: " + watch.toString());
        System.out.println("Hash: " + resp.data.hash);
    }
    
    private void place(Board board) {
        
    }
    
    private void move(Board board) {
        
    }
        
}

package net.aparsons.praetorian.rota.core;

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
        
    @Override
    public void run() {
        StopWatch watch = new StopWatch();

        Response resp;
        Board board;

        watch.start();
        // Reset until I get to play first
        do {
            resp = client.request(API.start());
            board = new Board(resp.data.board);
        } while(board.getComputerPieceCount() == 1);
       
        // Place initial pieces
        resp = client.request(API.place(1));
        print(resp.data.board);
        
        resp = client.request(API.place(5));
        print(resp.data.board);
        
        resp = client.request(API.place(6));
        print(resp.data.board);
        
        // Loop in a circle
        while (resp.data.hash == null) {
            System.out.println("Session Time: " + watch.toString());
            
            resp = client.request(API.move(5, 8));
            print(resp.data.board);
            
            resp = client.request(API.move(8, 7));
            print(resp.data.board);

            resp = client.request(API.move(7, 5));
            print(resp.data.board);
        }
        watch.stop();
        
        System.out.println("Took: " + watch.toString());
        System.out.println("Hash: " + resp.data.hash);
    }

    private void print(String board) {
        if (board != null) {
            System.out.println(new Board(board));
        }
    }
    
}

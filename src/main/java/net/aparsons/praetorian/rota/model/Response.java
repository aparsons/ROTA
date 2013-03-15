package net.aparsons.praetorian.rota.model;

public class Response {
    public String status;
    public Data data;

    @Override
    public String toString() {
        return status + " " + data;
    }
    
    public class Data {
        public String board;
        public int player_wins;
        public int computer_wins;
        public String hash;

        @Override
        public String toString() {
            return board + "," + player_wins + "," + computer_wins + "," + hash;
        }

    }
    
}

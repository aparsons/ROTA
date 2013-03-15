package net.aparsons.praetorian.rota.http;

public class API {
    
    private static String BASE_URL = "http://rota.praetorian.com/rota/service/play.php";
    
    public static String start() {
        return BASE_URL + "?request=new";
    }
    
    public static String place(int location) {
        return BASE_URL + "?request=place&location=" + location;
    }
    
    public static String move(int from, int to) {
        return BASE_URL + "?request=move&from=" + from + "&to=" + to;
    }
    
    public static String status() {
        return BASE_URL + "?request=status";
    }
}

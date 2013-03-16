package net.aparsons.praetorian.rota;

import net.aparsons.praetorian.rota.core.Controller;

public class App {
    
    // http://www.praetorian.com/challenge/rota/
    
    // Misc Notes:
    // called Three Mens Morris
    // If no session cookie, will return 500 error
    // API documentation incorrect
    // Placing piece at 9th position causes long response and causes reset
    public static void main(String[] args){        
        Controller c = new Controller();
        c.run();
    }
    
}

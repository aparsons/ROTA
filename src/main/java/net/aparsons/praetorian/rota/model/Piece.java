package net.aparsons.praetorian.rota.model;

public enum Piece {
    EMPTY("-"),
    COMPUTER("c"),
    PLAYER("p");
    
    private final String value;
    
    Piece(String value) {
        this.value = value;
    }    

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
    
    public static Piece getEnum(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        
        for (Piece p : values()) {
            if (s.equalsIgnoreCase(p.getValue())) {
                return p;
            }
        }
        throw new IllegalArgumentException();
    }
}

package com.quebec.core.cli.model;

public enum XWallLetter {
    S(0),
    T(1),
    U(2),
    V(3),
    W(4),
    X(5),
    Y(6),
    Z(7);

    private final int x;

    XWallLetter(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public static XWallLetter xLetterByX(int x) {
        for (XWallLetter xTileLetter : values()) {
            if (xTileLetter.getX() == x) {
                return xTileLetter;
            }
        }
        return null;
    }
}

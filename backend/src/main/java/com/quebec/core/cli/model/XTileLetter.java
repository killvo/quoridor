package com.quebec.core.cli.model;

public enum XTileLetter {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8);

    private final int x;

    XTileLetter(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public static XTileLetter xLetterByX(int x) {
        for (XTileLetter xTileLetter : values()) {
            if (xTileLetter.getX() == x) {
                return xTileLetter;
            }
        }
        return null;
    }
}

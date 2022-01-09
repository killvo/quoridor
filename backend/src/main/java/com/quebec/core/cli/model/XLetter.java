package com.quebec.core.cli.model;

public enum XLetter {
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

    XLetter(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public static XLetter xLetterByX(int x) {
        for (XLetter xLetter : values()) {
            if (xLetter.getX() == x) {
                return xLetter;
            }
        }
        return null;
    }
}

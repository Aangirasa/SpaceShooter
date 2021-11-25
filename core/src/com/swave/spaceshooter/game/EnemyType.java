package com.swave.spaceshooter.game;

public enum EnemyType {

    BASIC(10);

    public int getValue() {
        return value;
    }

    private int value;

    EnemyType(int value) {
        this.value = value;
    }

}

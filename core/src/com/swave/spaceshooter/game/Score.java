package com.swave.spaceshooter.game;

public class Score {
    private static Score INSTANCE = null;
    private int score;
    private float multiplier;

    private Score() {
        score = 0;
        multiplier = 1f;
    }

    public static Score getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Score();
        }
        return INSTANCE;
    }

    public void updateScore(int score){
        this.score+=score*multiplier;
    }

    public int getScore(){
        return score;
    }

    public void setMultiplier(float multiplier){
        this.multiplier = multiplier;
    }

    public float getMultiplier(){
        return multiplier;
    }
}

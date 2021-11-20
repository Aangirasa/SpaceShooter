package com.swave.spaceshooter.events;

import com.swave.spaceshooter.game.EnemyType;
import com.swave.spaceshooter.game.Score;

public class ScoreListener implements EventListener{

    private Score score;
    public ScoreListener() {
        score = Score.getInstance();
    }

    @Override
    public void listen(String type, Object gameObject) {
        System.out.println(type+" "+gameObject);
        EnemyType enemyType = (EnemyType) gameObject;
        score.updateScore(enemyType.getValue());
        System.out.println(score.getScore()+" ");
    }
}

package com.swave.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.swave.spaceshooter.events.EventManager;
import com.swave.spaceshooter.events.EventManagerImpl;
import com.swave.spaceshooter.events.ScoreListener;
import com.swave.spaceshooter.game.Background;
import com.swave.spaceshooter.game.EnemyManager;
import com.swave.spaceshooter.game.GameObject;
import com.swave.spaceshooter.game.PlayerManager;

public class SpaceShooter extends ApplicationAdapter {

    EnemyManager enemyManager;
    private SpriteBatch batch;
    private GameObject background;
    private GameObject playerManager;
    private EventManager eventManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        enemyManager = EnemyManager.getInstance();
        playerManager = PlayerManager.getInstance();
        eventManager = EventManagerImpl.getInstance();
        ScoreListener scoreListener = new ScoreListener();
        eventManager.subscribe("addPoints",scoreListener);
    }


    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.update(batch);
        playerManager.update(batch);
        enemyManager.update(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}

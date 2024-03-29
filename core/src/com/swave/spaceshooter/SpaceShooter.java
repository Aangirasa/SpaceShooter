package com.swave.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.swave.spaceshooter.events.*;
import com.swave.spaceshooter.game.*;

public class SpaceShooter extends ApplicationAdapter {

    EnemyManager enemyManager;
    private SpriteBatch batch;
    private GameObject background;
    private PlayerManager playerManager;
    private EventManager eventManager;
    private ExplosionPool explosionPool;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        enemyManager = EnemyManager.getInstance();
        playerManager = PlayerManager.getInstance();
        eventManager = EventManagerImpl.getInstance();
        explosionPool = ExplosionPool.getInstance();
        ScoreListener scoreListener = new ScoreListener();
        ExplosionListener explosionListener = new ExplosionListener();
        eventManager.subscribe(EventNames.EXPLODE, explosionListener);
        eventManager.subscribe(EventNames.ADD_POINTS, scoreListener);
    }


    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.update(batch);
        playerManager.update(batch);
        enemyManager.update(batch);
        explosionPool.update(batch);
        System.out.println(Gdx.graphics.getFramesPerSecond());
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

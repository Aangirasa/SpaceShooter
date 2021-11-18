package com.swave.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.swave.spaceshooter.game.*;

import java.util.ArrayList;
import java.util.List;

public class SpaceShooter extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameObject background;
	private GameObject player;
    private List<Enemy> enemies = new ArrayList<>();
    BulletManager bulletManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        bulletManager = new BulletManager(new Vector2(0,-1),120);
        bulletManager.direction = new Vector2(0,-1);
        enemies.add(new Enemy(bulletManager,new Vector2(232,750+300),new Vector2(232,650)));
        enemies.add(new Enemy(bulletManager,new Vector2(132,800+300),new Vector2(132,700)));
        enemies.add(new Enemy(bulletManager,new Vector2(332,800+300),new Vector2(332,700)));
        enemies.add(new Enemy(bulletManager,new Vector2(430,850+300),new Vector2(430,750)));
        enemies.add(new Enemy(bulletManager,new Vector2(30,850+300),new Vector2(30,750)));
        enemies.add(new Enemy(bulletManager,new Vector2(430+100,950),new Vector2(365,800)));
        enemies.add(new Enemy(bulletManager,new Vector2(30-100,950),new Vector2(90,800)));



        player = new Player(new Vector2(250-20,150),new Texture("ship1.png"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.update(batch);
        player.update(batch);
        enemies.forEach(enemy -> enemy.update(batch));
        System.out.println(Gdx.graphics.getFramesPerSecond());
        bulletManager.update(batch);
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

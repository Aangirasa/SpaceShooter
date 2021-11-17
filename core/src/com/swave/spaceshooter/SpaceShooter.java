package com.swave.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.swave.spaceshooter.game.Background;
import com.swave.spaceshooter.game.GameObject;
import com.swave.spaceshooter.game.Player;

public class SpaceShooter extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameObject background;
	private GameObject player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
		player = new Player(new Vector2(250-20,150),new Texture("ship1.png"));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        background.update(batch);
        player.update(batch);
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

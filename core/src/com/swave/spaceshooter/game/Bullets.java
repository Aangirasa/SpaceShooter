package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Bullets extends GameObject{
    public boolean isActive = true;
    private static final int SPEED = 300;

    public Bullets(Vector2 transform, Texture sprite) {
        super(transform, sprite);
    }

    @Override
    public void update(Batch batch) {
        if(isActive && transform.y<1000) {
            transform.y += SPEED * Gdx.graphics.getDeltaTime();
            batch.draw(texture, transform.x, transform.y);
        }else {
            isActive = false;
        }
    }
}

package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
    private static final int SPEED = 300;
    public boolean isActive = true;
    public Vector2 transform;
    public Vector2 direction;
    private static Texture texture;

    public Bullets(Vector2 transform) {
        this.transform = transform;
    }

    public void update(Batch batch) {
        if(isActive && direction.y==-1 && transform.y <0){
            isActive = false;
        }
        if(isActive && direction.y==1 && transform.y> 1000){
            isActive = false;
        }
        if (isActive) {
            transform.y += SPEED * Gdx.graphics.getDeltaTime() *direction.y;
            batch.draw(texture, transform.x, transform.y);
        }
    }

    public static void updateTexture(Texture newTexture){
        texture = newTexture;
    }
}

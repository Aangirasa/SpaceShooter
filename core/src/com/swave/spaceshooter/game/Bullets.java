package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
    private static final int SPEED = 150;
    private static Texture texture;
    public boolean isActive = true;
    public Vector2 transform;
    public Vector2 direction;
    public Rectangle boundingBox = new Rectangle(0f, 0f, 17f, 17f);

    public Bullets(Vector2 transform) {
        this.transform = transform;
    }

    public static void updateTexture(Texture newTexture) {
        texture = newTexture;
    }

    public void update(Batch batch) {
        if (isActive && direction.y == -1 && transform.y < 0) {
            isActive = false;
        }
        if (isActive && direction.y == 1 && transform.y > 1000) {
            isActive = false;
        }
        if (isActive) {
            System.out.println(transform.toString());
            transform.y += SPEED * Gdx.graphics.getDeltaTime() * direction.y;
            boundingBox.setPosition(transform.x, transform.y);
            batch.draw(texture, transform.x, transform.y);
        }
    }
}

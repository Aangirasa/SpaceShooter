package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
    private static final int SPEED = 500;
    private static Texture texture;
    public boolean isActive = true;
    public float strength;
    public Vector2 transform;
    public Vector2 direction;
    public Rectangle boundingBox = new Rectangle(0f, 0f, 15f, 15f);

    public Bullets(Vector2 transform, int strength) {
        this.transform = transform;
        this.strength = strength;
    }

    public static void updateTexture(Texture newTexture) {
        texture = newTexture;
    }

    public void update(Batch batch) {
        if (!isActive) {
            return;
        }
        drawBullets(batch);
        deactivateIfOutOfBounds();
    }

    private void drawBullets(Batch batch) {
        transform.y += SPEED * Gdx.graphics.getDeltaTime() * direction.y;
        boundingBox.setPosition(transform.x, transform.y);
        batch.draw(texture, transform.x, transform.y);
    }

    private void deactivateIfOutOfBounds() {
        if (direction.y == -1 && transform.y < 0) {
            isActive = false;
        }
        if (direction.y == 1 && transform.y > 1000) {
            isActive = false;
        }
    }
}

package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.xml.bind.ValidationEvent;

public class Bullets extends GameObject {
    private static final int SPEED = 500;
    public float strength=5;
    public Vector2 direction;
    public Rectangle boundingBox = new Rectangle(0f, 0f, 15f, 15f);

    public Bullets(Vector2 transform, Texture sprite) {
        super(new Vector2(transform), sprite);
    }

    @Override
    public void update(Batch batch) {
        if (isActive && direction.y == -1 && transform.y < 0) {
            isActive = false;
        }
        if (isActive && direction.y == 1 && transform.y > 1000) {
            isActive = false;
        }
        if (isActive) {
            transform.y += SPEED * Gdx.graphics.getDeltaTime() * direction.y;
            boundingBox.setPosition(transform.x, transform.y);
            batch.draw(texture, transform.x, transform.y);
        }
    }
}

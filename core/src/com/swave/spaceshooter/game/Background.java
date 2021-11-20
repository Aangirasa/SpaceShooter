package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Background extends GameObject {
    private final int moveSpeed = 150;
    private Texture bg2;
    private int yBg2 = 1500;

    public Background(Vector2 transform, Texture texture) {
        super(transform, texture);
    }

    public Background() {
        super(new Vector2(0f, 0f), new Texture("bg.png"));
        bg2 = new Texture("bg.png");
    }

    @Override
    public void update(Batch batch) {
        //simulating Infinte Scroll with 2 Backgrounds
        if (transform.y <= -1500) {
            transform.y = yBg2 * -1;
            yBg2 = 0;
        }
        transform.y -= (moveSpeed * Gdx.graphics.getDeltaTime());
        yBg2 = (int) (transform.y + 1500);
        batch.draw(texture, transform.x, transform.y);
        batch.draw(bg2, transform.x, yBg2);
    }
}

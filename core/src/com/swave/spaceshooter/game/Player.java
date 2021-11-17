package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
    public int health = 100;
    public int MOVEMENT_SPEED = 120;


    public Player(Vector2 transform, Texture sprite) {
        super(transform, sprite);
    }

    @Override
    public void update(Batch batch) {
        Vector2 inputs = handleInput();
        transform.x -= (inputs.x * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime());
        batch.draw(texture, transform.x, transform.y);
    }

    private Vector2 handleInput() {
        int x = 0;
        int y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            x = 1;
        } if (Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            x = -1;
        }
        return new Vector2(x, y);
    }
}

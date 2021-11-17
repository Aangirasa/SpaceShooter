package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
    private final BulletPool bulletPool = new BulletPool();
    public int health = 100;
    public int MOVEMENT_SPEED = 120;
    public float fireRate = 0.1f;
    public float coolDown = fireRate;

    public Player(Vector2 transform, Texture sprite) {
        super(transform, sprite);
    }

    @Override
    public void update(Batch batch) {
        Vector2 inputs = handleInput();
        transform.x -= (inputs.x * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime());
        transform.y -= (inputs.y * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            coolDown -= Gdx.graphics.getDeltaTime();
            if (coolDown < 0) {
                bulletPool.addResources(new Vector2(transform.x - 5, transform.y + 10));
                bulletPool.addResources(new Vector2(transform.x + 13, transform.y + 10));
                coolDown = fireRate;
            }
        }
        bulletPool.update(batch);
        batch.draw(texture, transform.x, transform.y);
    }

    private Vector2 handleInput() {
        int x = 0;
        int y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            x = 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            x = -2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            y = -2;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S)) {
            y = 2;
        }
        return new Vector2(x, y);
    }
}

package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
    private static final Texture shipLeft = new Texture("ship0.png");
    private static final Texture shipRight = new Texture("ship4.png");
    private final BulletManager bulletManager = new BulletManager();
    public int health = 100;
    public int MOVEMENT_SPEED = 120;
    public float fireRate = 0.1f;
    public float coolDown = fireRate;


    public Player(Vector2 transform, Texture sprite) {
        super(transform, sprite);
    }

    @Override
    public void update(Batch batch) {
        Vector2 inputs = handleMovementInputs();
        Texture currentFrame = this.texture;
        if (inputs.x != 0) {
            currentFrame = inputs.x < 0 ? shipLeft : shipRight;
            transform.x -= (inputs.x * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime());
        }
        transform.y -= (inputs.y * MOVEMENT_SPEED * Gdx.graphics.getDeltaTime());
        handleBulletFires();
        bulletManager.update(batch);
        batch.draw(currentFrame, transform.x, transform.y);
    }

    private void handleBulletFires() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            coolDown -= Gdx.graphics.getDeltaTime();
            if (coolDown < 0) {
                bulletManager.addResources(new Vector2(transform.x - 5, transform.y + 10));
                bulletManager.addResources(new Vector2(transform.x + 13, transform.y + 10));
                coolDown = fireRate;
            }
        }
    }

    private Vector2 handleMovementInputs() {
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

package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class PlayerManager extends GameObject {
    private static final Texture shipLeft = new Texture("ship0.png");
    private static final Texture shipRight = new Texture("ship4.png");
    public static int health = 100;
    public static Rectangle boundingBox = new Rectangle(0f, 0f, 15f, 25f);
    private static PlayerManager INSTANCE = null;
    public final BulletManager bulletManager = new BulletManager(new Vector2(0, 1), 70);
    public int MOVEMENT_SPEED = 120;
    public float fireRate = 0.1f;
    public float coolDown = fireRate;

    private PlayerManager() {
        super(new Vector2(250 - 20, 150), new Texture("ship1.png"));
    }

    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
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
        boundingBox.setPosition(transform.x, transform.y);
        detectCollision(EnemyManager.getInstance().bulletManager.getBulletsPool());
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

    public void detectCollision(List<Bullets> bulletsList) {
        bulletsList.forEach(bullets -> {
            if (boundingBox.overlaps(bullets.boundingBox)) {
                health--;
            }
        });
    }

}

package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    static Texture texture = new Texture("parafighter.png");
    static int MOVEMENT_SPEED = 150;
    private final Rectangle boundingBox = new Rectangle(0f, 0f, 32f, 33f);
    public int health = 100;
    public Vector2 transform;
    //private float remainingTime = 10f;
    public boolean isActive = true;
    public EnemyType enemyType = EnemyType.BASIC;
    Vector2 defaultBulletVector = new Vector2(0, 0);
    private final Vector2 target;
    private final float fireRate = 0.6f;
    private float coolDown = fireRate;
    private final BulletManager bulletManager;

    public Enemy(BulletManager bulletManager, Vector2 transform, Vector2 target) {
        this.transform = transform;
        this.target = target;
        this.bulletManager = bulletManager;
    }

    public void update(Batch batch) {
        if (!isActive) {
            return;
        }
        //remainingTime -= Gdx.graphics.getDeltaTime();
        coolDown -= Gdx.graphics.getDeltaTime();
        //transform.x = (float) Math.sin(transform.y / 11) * 125 + 250;
        //transform.y -= (float) Math.sin(transform.x);
        if (coolDown < 0) {
            bulletManager.addResources(defaultBulletVector.set(transform.x + 5, transform.y + 5));
            coolDown = fireRate;
        }
       /* if(remainingTime<0){
            transform.y+= Gdx.graphics.getDeltaTime() * MOVEMENT_SPEED;
            batch.draw(texture,transform.x,transform.y);
            return;
        }*/
        transform.y -= Gdx.graphics.getDeltaTime() * MOVEMENT_SPEED * 0.3;

        if ((int) transform.x != (int) target.x) {
            transform.x = transform.x > target.x ?
                    transform.x - Gdx.graphics.getDeltaTime() * MOVEMENT_SPEED
                    : transform.x + Gdx.graphics.getDeltaTime() * MOVEMENT_SPEED;
        }
        boundingBox.setPosition(transform.x, transform.y);
        batch.draw(texture, transform.x, transform.y);
    }

    public boolean isCollision(Rectangle otherBox) {
        return boundingBox.overlaps(otherBox);
    }
}

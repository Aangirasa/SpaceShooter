package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    static Texture texture = new Texture("parafighter.png");
    Vector2 transform;
    Vector2 target;
    static int MOVEMENT_SPEED = 150;
    float remainingTime = 10f;
    float fireRate = 0.3f;
    float coolDown = fireRate;

    BulletManager bulletManager;

    public Enemy(BulletManager bulletManager,Vector2 transform,Vector2 target) {
        this.transform = transform;
        this.target = target;
        this.bulletManager = bulletManager;
    }

    public void update(Batch batch){
        remainingTime-=Gdx.graphics.getDeltaTime();
        coolDown-=Gdx.graphics.getDeltaTime();
        if(coolDown<0){
            bulletManager.addResources(new Vector2(transform.x+5,transform.y+5));
            coolDown = fireRate;
        }
        if(remainingTime<0){
            transform.y+= Gdx.graphics.getDeltaTime() * MOVEMENT_SPEED;
            batch.draw(texture,transform.x,transform.y);
            return;
        }
        if(transform.y> target.y){
            transform.y-= Gdx.graphics.getDeltaTime()*MOVEMENT_SPEED;
        }
        if((int)transform.x != (int)target.x){
           transform.x = transform.x > target.x?
                   transform.x-Gdx.graphics.getDeltaTime()*MOVEMENT_SPEED
                   :transform.x+Gdx.graphics.getDeltaTime()*MOVEMENT_SPEED;
        }
        batch.draw(texture,transform.x,transform.y);
    }
}

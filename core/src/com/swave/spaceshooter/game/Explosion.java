package com.swave.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Explosion extends GameObject{
    private final static TextureAtlas textureAtlas = new TextureAtlas("explosion.atlas");
    private final static Animation animation = new Animation(1 / 11f, textureAtlas.getRegions());
    private float elapsedTime = 0f;

    public Explosion(Vector2 transform) {
        super(new Vector2(transform),null);
        this.transform = transform;
    }

    public void update(Batch batch) {
        if (!isActive)
            return;
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, false), transform.x, transform.y, 48f, 48f);
        if (animation.isAnimationFinished(elapsedTime)) {
            isActive = false;
            elapsedTime = 0f;
        }
    }

    public void setTransform(Vector2 transform) {
        this.transform.x = transform.x - 5;
        this.transform.y = transform.y;
    }
}

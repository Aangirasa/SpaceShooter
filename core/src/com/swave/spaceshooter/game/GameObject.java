package com.swave.spaceshooter.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    public Vector2 transform;
    public Texture texture;
    public boolean isActive = true;


    public GameObject(Vector2 transform, Texture sprite) {
        this.transform = transform;
        this.texture = sprite;
    }

    public abstract void update(Batch batch);

    void resetTransform(Vector2 position) {
        this.transform = position;
    }
}

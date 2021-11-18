package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    public Vector2 direction;
    private final List<Bullets> pool = new ArrayList<>();

    public BulletManager(Vector2 direction,int MAX) {
        this.direction = direction;
        Bullets.updateTexture(new Texture("shotoval.png"));
        for (int i = 0; i < MAX; i++) {
            Bullets b = new Bullets(new Vector2(0f, 1505f));
            b.direction = direction;
            pool.add(b);
        }
    }

    public void addResources(Vector2 position) {
        Bullets bullets = pool.stream().filter(b -> !b.isActive).findFirst().orElse(null);
        if (bullets != null) {
            bullets.isActive = true;
            bullets.transform = position;
        }
    }

    public void update(Batch batch) {
        for (Bullets b : pool) {
            b.update(batch);
        }
    }
}

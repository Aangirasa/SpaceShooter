package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private final List<Bullets> pool = new ArrayList<>();
    public Vector2 direction;

    public BulletManager(Vector2 direction, int MAX, int strength) {
        this.direction = direction;
        Bullets.updateTexture(new Texture("ship0.png"));
        for (int i = 0; i < MAX; i++) {
            Bullets b = new Bullets(new Vector2(0f, 1505f), strength);
            b.direction = direction;
            pool.add(b);
        }
    }

    public void addResources(Vector2 position) {
        for (Bullets bullets : pool) {
            if (!bullets.isActive) {
                bullets.isActive = true;
                bullets.transform.set(position.x, position.y);
                break;
            }
        }

    }

    public void update(Batch batch) {
        for (Bullets b : pool) {
            b.update(batch);
        }
    }

    public List<Bullets> getBulletsPool() {
        return pool;
    }
}

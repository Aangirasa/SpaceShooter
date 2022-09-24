package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ExplosionPool {
    private static ExplosionPool INSTANCE = null;
    private final Vector2 defaultPosition = new Vector2(1500, 1500);
    private final int MAX = 10;
    List<Explosion> explosions = new ArrayList<>();

    private ExplosionPool() {
        for (int i = 0; i < MAX; i++) {
            Explosion explosion = new Explosion(defaultPosition);
            explosions.add(explosion);
        }
    }

    public static ExplosionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExplosionPool();
        }
        return INSTANCE;
    }

    public void update(Batch batch) {
        explosions.forEach(explosion -> {
            if (explosion.isActive) {
                explosion.update(batch);
            }
        });
    }

    public void setExplosionsAt(Vector2 position) {
        for (Explosion explosion : explosions) {
            if (!explosion.isActive) {
                explosion.isActive = true;
                explosion.setTransform(position);
                break;
            }
        }
    }
}

package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletManager {
    private final List<Bullets> pool = new ArrayList<>();
    int MAX = 70;

    public BulletManager() {
        Bullets.updateTexture(new Texture("shotoval.png"));
        for (int i = 0; i < MAX; i++) {
            pool.add(new Bullets(new Vector2(0f, 1505f)));
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

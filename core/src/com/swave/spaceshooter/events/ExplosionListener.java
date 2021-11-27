package com.swave.spaceshooter.events;

import com.badlogic.gdx.math.Vector2;
import com.swave.spaceshooter.game.Explosion;
import com.swave.spaceshooter.game.ObjectPool;

public class ExplosionListener implements EventListener {

    private final ObjectPool<Explosion> explosionPool;

    public ExplosionListener(ObjectPool<Explosion> explosionPool) {
        this.explosionPool = explosionPool;
    }

    @Override
    public void listen(String type, Object gameObject) {
        explosionPool.getResource((Vector2) gameObject);
    }
}

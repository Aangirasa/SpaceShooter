package com.swave.spaceshooter.events;

import com.badlogic.gdx.math.Vector2;
import com.swave.spaceshooter.game.ExplosionPool;

public class ExplosionListener implements EventListener {
    private static final ExplosionPool explosionPool = ExplosionPool.getInstance();

    @Override
    public void listen(String type, Object gameObject) {
        explosionPool.setExplosionsAt((Vector2) gameObject);
    }
}

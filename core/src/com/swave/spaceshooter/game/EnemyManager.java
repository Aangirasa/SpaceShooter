package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.swave.spaceshooter.events.EventManager;
import com.swave.spaceshooter.events.EventManagerImpl;

import java.util.ArrayList;
import java.util.List;

import static com.swave.spaceshooter.events.EventNames.ADD_POINTS;
import static com.swave.spaceshooter.events.EventNames.EXPLODE;

public class EnemyManager {

    private static EnemyManager INSTANCE = null;
    private final List<Enemy> enemies;
    private final EventManager eventManager = EventManagerImpl.getInstance();
    public ObjectPool<Bullets> bulletPool;
    private Vector2 offScreenPosition = new Vector2(0,1500);
    private Vector2 enemyDirection = new Vector2(0, -1);


    private EnemyManager() {
        this.enemies = new ArrayList<>();
        this.bulletPool = new ObjectPool<>(120,()->{
                    Bullets bullets = new Bullets(offScreenPosition,new Texture("shotoval.png"));
                    bullets.direction = enemyDirection;
                    bullets.isActive = false;
                    return bullets;
        });
        //bulletManager.direction = new Vector2(0, -1);
        spawnEnemies();
    }

    public static EnemyManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EnemyManager();
        }
        return INSTANCE;
    }

    private void spawnEnemies() {
        enemies.add(new Enemy(bulletPool, new Vector2(232, 750 + 300), new Vector2(232, 650)));
        enemies.add(new Enemy(bulletPool, new Vector2(132, 800 + 300), new Vector2(132, 700)));
        enemies.add(new Enemy(bulletPool, new Vector2(332, 800 + 300), new Vector2(332, 700)));
        enemies.add(new Enemy(bulletPool, new Vector2(430, 850 + 300), new Vector2(430, 750)));
        enemies.add(new Enemy(bulletPool, new Vector2(30, 850 + 300), new Vector2(30, 750)));
        enemies.add(new Enemy(bulletPool, new Vector2(430 + 100, 950), new Vector2(365, 800)));
        enemies.add(new Enemy(bulletPool, new Vector2(30 - 100, 950), new Vector2(90, 800)));
    }

    public void update(Batch batch) {
        bulletPool.update(batch);
        detectCollisions(PlayerManager.getInstance().bulletPool.getGameObjects());
        enemies.forEach(enemy -> {
            enemy.update(batch);
        });
    }

    public void detectCollisions(List<Bullets> playerBullets) {
        for (Bullets bullet : playerBullets) {
            for (Enemy enemy : enemies) {
                if (enemy.isActive) {
                    if (bullet.isActive && enemy.isCollision(bullet.boundingBox)) {
                        enemy.health -= 10;
                        bullet.isActive = false;
                    }
                    if (enemy.health < 0) {
                        enemy.isActive = false;
                        eventManager.notify(ADD_POINTS, enemy.enemyType);
                        eventManager.notify(EXPLODE, enemy.transform);
                    }
                }
            }
        }
    }

}

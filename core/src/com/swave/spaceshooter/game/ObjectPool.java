package com.swave.spaceshooter.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ObjectPool<T extends GameObject> {
    private final List<T> resource;

    public ObjectPool(int MAX, Supplier<T> instantiateMethod) {
        this.resource = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            resource.add(instantiateMethod.get());
        }
    }

    public T getResource(Vector2 transform) {
        T gameObject = resource.stream()
                .filter(resource -> !resource.isActive)
                .findFirst()
                .orElse(null);
        if(gameObject != null){
            gameObject.transform.set(transform);
            gameObject.isActive = true;
        }
        return gameObject;
    }


    public void update(Batch batch) {
        resource.forEach(gameObject -> {
            if (gameObject.isActive) {
                gameObject.update(batch);
            }
        });
    }

    public List<T> getGameObjects(){
        return resource;
    }
}

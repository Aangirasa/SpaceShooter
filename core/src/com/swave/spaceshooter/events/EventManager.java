package com.swave.spaceshooter.events;

public interface EventManager {
    void subscribe(String type, EventListener eventListener);
    void unsubscribe(String type, java.util.EventListener eventListener);
    void notify(String type,Object gameObject);
}

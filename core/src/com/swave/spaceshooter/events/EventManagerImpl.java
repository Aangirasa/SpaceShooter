package com.swave.spaceshooter.events;

import java.util.*;

public class EventManagerImpl implements EventManager {
    private static EventManager INSTANCE = null;
    private final Map<String, List<EventListener>> listenersContext;

    public EventManagerImpl() {
        listenersContext = new HashMap<>();
    }

    public static EventManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventManagerImpl();
        }
        return INSTANCE;
    }

    @Override
    public void subscribe(String type, EventListener eventListener) {
        if (listenersContext.containsKey(type)) {
            listenersContext.get(type).add(eventListener);
        } else {
            listenersContext.put(type, Arrays.asList(eventListener));
        }
    }

    @Override
    public void unsubscribe(String type, java.util.EventListener eventListener) {
        listenersContext.getOrDefault(type, new ArrayList<>()).remove(eventListener);
    }

    @Override
    public void notify(String type, Object gameObject) {
        listenersContext.getOrDefault(type, new ArrayList<>()).forEach(eventListener -> {
            eventListener.listen(type, gameObject);
        });
    }
}

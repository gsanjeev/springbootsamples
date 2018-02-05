package com.example.laxtech.keyvaluestore;

import java.util.Map;

public interface RedisRepository {

    /**
     * Return all Actors
     */
    Map<Object, Object> findAllActors();

    /**
     * Add key-value pair to Redis.
     */
    void add(Actor actor);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);

    /**
     * find a actor
     */
    Actor findActor(String id);

}
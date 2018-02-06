package com.example.laxtech.keyvaluestore;

import java.util.Map;

public interface ActorRepository {

    /**
     * Return all Actors
     */
    Map<Object, Object> findAllActors();

    /**
     * Add key-value pair to Redis.
     */
    void addActor(Actor actor);

    /**
     * Update key-value pair to Redis.
     */
    void updateActor(Actor actor);

    /**
     * Delete a key-value pair in Redis.
     */
    void deleteActor(String id);

    /**
     * find a actor
     */
    Actor findActor(String id);

}
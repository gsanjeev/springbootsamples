package com.example.laxtech.keyvaluestore;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.redis.core.HashOperations;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.stereotype.Repository;


        import java.util.Map;
        import javax.annotation.PostConstruct;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
    private static final String KEY = "Actor";

    private RedisTemplate<String, Actor> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public ActorRepositoryImpl(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void addActor(final Actor actor) {
        hashOperations.put(KEY, actor.getId(), actor);
    }

    public void updateActor(Actor actor) {
        hashOperations.put(KEY, actor.getId(), actor);
    }

    public void deleteActor(final String id) {
        hashOperations.delete(KEY, id);
    }

    public Actor findActor(final String id){
        return (Actor) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllActors(){
        return hashOperations.entries(KEY);
    }


}

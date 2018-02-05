package com.example.laxtech.keyvaluestore;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.redis.core.HashOperations;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.stereotype.Repository;


        import java.util.Map;
        import javax.annotation.PostConstruct;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEY = "Actor";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final Actor actor) {
        hashOperations.put(KEY, actor.getId(), actor.getName());
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    public Actor findActor(final String id){
        return (Actor) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllActors(){
        return hashOperations.entries(KEY);
    }


}

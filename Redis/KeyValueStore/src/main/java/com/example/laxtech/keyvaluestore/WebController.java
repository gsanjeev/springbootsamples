package com.example.laxtech.keyvaluestore;

import java.util.HashMap;
        import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ActorRepository redisRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/keys")
    public @ResponseBody Map<Object, Object> keys() {
        return redisRepository.findAllActors();
    }

    @RequestMapping("/values")
    public @ResponseBody Map<String, Actor> findAll() {
        Map<Object, Object> allActors = redisRepository.findAllActors();
        Map<String, Actor> map = new HashMap<String, Actor>();
        for(Map.Entry<Object, Object> entry : allActors.entrySet()){
            String key = (String) entry.getKey();
            map.put(key, (Actor)allActors.get(key));
            System.out.println("key: " + ((Actor)allActors.get(key)).getId() +
                    "name: " +((Actor)allActors.get(key)).getName() +
                    "age: " +((Actor)allActors.get(key)).getAge());

        }
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @RequestParam String key,
            @RequestParam String name,
            @RequestParam Integer age) {
System.out.println("key: " + key + "name: " +name + "age: " + age);
        Actor actor = new Actor(key, name, age);

        redisRepository.addActor(actor);
        System.out.println("key: " + actor.getId() + "name: " +actor.getName() + "age: " +actor.getAge());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> update(
            @RequestParam String key,
            @RequestParam String name,
            @RequestParam Integer age) {

        Actor actor = new Actor(key, name, age);

        redisRepository.updateActor(actor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepository.deleteActor(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

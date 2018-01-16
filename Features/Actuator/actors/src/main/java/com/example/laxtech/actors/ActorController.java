package com.example.laxtech.actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/")
public class ActorController {

    @Autowired
    ActorRepository repository;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    // Get All Actors
    @RequestMapping(value="/actors", method= RequestMethod.GET)
    public List<Actor> getAllActors() {
        List<Actor> actorList = new ArrayList<>();

        repository.findAll().forEach(actorList::add);
        return actorList;
    }

    //Save actor
    @RequestMapping(value="/actors", method= RequestMethod.POST)
    public Actor createActor(@RequestBody Actor actor) {
        return repository.save(actor);
    }



}

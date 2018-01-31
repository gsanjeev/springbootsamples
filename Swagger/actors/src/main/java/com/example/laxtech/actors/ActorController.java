package com.example.laxtech.actors;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/")
public class ActorController {

    private static  List<Actor> actors= new ArrayList<>();

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    // Get All Actors
    @ApiOperation(value = "getActors", notes="get all actors",nickname = "getActors")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Actors service not found"),
            @ApiResponse(code = 200, message = "Actors successfully retrieved",
                    response = Actor.class, responseContainer = "List") })
    @RequestMapping(value="/actors", method= RequestMethod.GET)
    public List<Actor> getAllActors() {
        List<Actor> actorList = new ArrayList<>();

        actors.forEach(actorList::add);
        return actorList;
    }

    //Save actor
    @ApiOperation(value = "createActor", notes="create new actor",nickname = "saveActor")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Actor service not found"),
            @ApiResponse(code = 200, message = "Actor successfully created",
                    response = Actor.class) })
    @RequestMapping(value="/actors", method= RequestMethod.POST)
    public Actor createActor(@ApiParam(value = "new actor object",
            required = true) @RequestBody Actor actor) {

        actors.add(actor);
        return actor;
    }

    static {
        for(int i=1; i<10; i++) {
            Actor actor = new Actor();
            actor.setId(Long.valueOf(i));
            actor.setAge(i+20);
            actor.setName("Name" + i);
            actors.add(actor);
        }

    }

}

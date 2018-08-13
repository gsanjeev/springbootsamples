package com.example.laxtech.prometheus.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example1")
public class MemberController {

    @Timed(
            value = "com.example.laxtech.example1.member",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/member")
    public String getMember() {
        return "Hello member 1";
    }

    @Timed(
            value = "com.example.laxtech.example1.game",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/game")
    public String getGames() {
        return "This is game 1";
    }

    @Timed(
            value = "com.example.laxtech.example1.game",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/turnament")
    public String getTurnaments() {
        return "This is turnament 1";
    }

    @GetMapping("/ground")
    public String getGrounds() {
        return "This is ground 1";
    }
}

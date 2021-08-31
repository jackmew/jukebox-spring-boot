package com.zestlifia.pop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
public class ArtistsController {

    private final List<String> artists = Stream.of("Lady Gaga", "Madonna")
            .collect(Collectors.toList());

    private final Environment env;

    public ArtistsController(Environment env) {
        this.env = env;
    }


    @GetMapping("/")
    String metal() {
        return "pop";
    }

    @GetMapping("/artists")
    List<String> getArtists() {
        // --artists=jack,mew
        String artistsWithDot = env.getProperty("ARTISTS");
        if (artistsWithDot == null) {
            log.info(artists.toString());
            return artists;
        }
        String[] artistsInput = artistsWithDot.split(",");
        List<String> artistsList = Arrays.asList(artistsInput);
        log.info(artistsList.toString());
        return artistsList;

    }
    @GetMapping("/ping")
    String ping() {
        log.info("ping to metal requested, responding with HTTP 200");
        return "HTTP 200 ok";
    }
}

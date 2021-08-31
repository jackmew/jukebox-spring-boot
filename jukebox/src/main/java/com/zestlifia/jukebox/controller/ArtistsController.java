package com.zestlifia.jukebox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ArtistsController {

    private final Environment env;
    private final RestTemplate restTemplate;
    String metal_host = "127.0.0.1:8081";
    String pop_host = "127.0.0.1:8082";

    public ArtistsController(Environment env) {
        this.env = env;
        this.restTemplate = new RestTemplate();
    }


    @GetMapping("/")
    String jukebox() {
        return "jukebox";
    }

    @GetMapping("/metal")
    String getMetalArtists() {
        String METAL_HOST = env.getProperty("METAL_HOST");
        if (METAL_HOST != null) {
            metal_host = METAL_HOST;
        }

        String MetalResourceUrl = "http://"+ metal_host +"/artists";
        ResponseEntity<String> response;

        try {
            response = restTemplate.getForEntity(MetalResourceUrl, String.class);
            log.info(MetalResourceUrl+ " " +response.getStatusCode().toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }

        return response.getBody();
    }
    @GetMapping("/pop")
    String getPopArtists() {
        String POP_HOST = env.getProperty("POP_HOST");
        if (POP_HOST != null) {
            pop_host = POP_HOST;
        }

        String PopResourceUrl = "http://"+ pop_host +"/artists";
        ResponseEntity<String> response;

        try {
            response = restTemplate.getForEntity(PopResourceUrl, String.class);
            log.info(PopResourceUrl+" "+response.getStatusCode().toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return e.getMessage();
        }

        return response.getBody();
    }
}

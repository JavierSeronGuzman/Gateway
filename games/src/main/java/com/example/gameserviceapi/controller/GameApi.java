package com.example.gameserviceapi.controller;


import com.example.gameserviceapi.commons.contants.ApiPathVariables;
import com.example.gameserviceapi.commons.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// v1/games
@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.GAME_ROUTE)
public interface GameApi {
    @PostMapping
    ResponseEntity<Game> saveGame(@RequestHeader("userIdRequest") String userId,@RequestBody Game game);
    @GetMapping("/{id}")
    ResponseEntity<Game> getGameById(@PathVariable String id);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteGameById(@PathVariable String id);
    @GetMapping
    ResponseEntity<List<Game>> getAllGames();
    @PutMapping
    ResponseEntity<Game> updateGame(@RequestBody Game game);
}

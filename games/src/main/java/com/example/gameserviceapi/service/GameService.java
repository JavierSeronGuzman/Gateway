package com.example.gameserviceapi.service;

import com.example.gameserviceapi.commons.entities.Game;

import java.util.List;

public interface GameService {
    Game saveGame(String userId, Game gameRequest);

    Game getGameById(String id);

    void deleteGameById(String id);

    List<Game> getAllGames();
    Game updateGame(Game gameRequest);
}

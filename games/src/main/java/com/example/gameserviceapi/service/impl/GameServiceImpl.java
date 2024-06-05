package com.example.gameserviceapi.service.impl;

import com.example.gameserviceapi.commons.entities.Game;
import com.example.gameserviceapi.commons.exceptions.GameException;
import com.example.gameserviceapi.repository.GameRepository;
import com.example.gameserviceapi.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game saveGame(String userId, Game gameRequest) {
        gameRequest.setUserId(Integer.parseInt(userId));
        return this.gameRepository.save(gameRequest);
    }

    @Override
    public Game getGameById(String id) {
        return this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));
    }

    @Override
    public void deleteGameById(String id) {
        Optional.of(id)
                .map(gameId -> {
                    this.gameRepository.deleteById(Long.valueOf(id));
                    return gameId;
                })
                .orElseThrow(() -> new GameException(HttpStatus.BAD_REQUEST, "Error finding game"));
    }

    @Override
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game updateGame(Game gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error updating game"));
    }

    private Game mapToEntity(Game gameRequested) {
        return Game.builder()
                .id(gameRequested.getId())
                .name(gameRequested.getName())
                .userId(gameRequested.getUserId())
                .build();
    }
}


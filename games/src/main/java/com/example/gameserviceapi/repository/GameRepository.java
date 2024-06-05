package com.example.gameserviceapi.repository;

import com.example.gameserviceapi.commons.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

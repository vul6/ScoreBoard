package org.example.scoreboard.service;

import org.example.scoreboard.dto.GameDto;
import org.example.scoreboard.model.Game;

import java.util.function.Function;

public class GameConverter implements Function<Game, GameDto> {

    @Override
    public GameDto apply(Game game) {
        return GameDto.builder()
                .homeTeam(game.getGameId().getHomeTeam())
                .awayTeam(game.getGameId().getAwayTeam())
                .homeTeamScore(game.getHomeTeamScore())
                .awayTeamScore(game.getAwayTeamScore())
                .build();
    }
}

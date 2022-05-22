package org.example.scoreboard.service;

import org.example.scoreboard.dto.GameDto;
import org.example.scoreboard.model.Game;

import java.util.function.Function;

public class GameConverter implements Function<Game, GameDto> {

    @Override
    public GameDto apply(Game game) {
        return GameDto.builder()
                .homeTeam(game.getHomeTeam().getTeamName())
                .homeTeamScore(game.getHomeTeam().getScore())
                .awayTeam(game.getAwayTeam().getTeamName())
                .awayTeamScore(game.getAwayTeam().getScore())
                .build();
    }
}

package org.example.scoreboard.service;

import org.example.scoreboard.dto.GameDto;
import org.example.scoreboard.model.Game;
import org.example.scoreboard.model.GameId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameConverterTest {

    private static final int HOME_SCORE = 2;
    private static final int AWAY_SCORE = 3;
    private static final String AWAY_TEAM = "France";
    private static final String HOME_TEAM = "England";

    private final GameConverter unit = new GameConverter();

    @Test
    void shouldConvertGameToGameDto() {
        //given
        GameId gameId = GameId.builder().awayTeam(AWAY_TEAM).homeTeam(HOME_TEAM).build();
        Game game = Game.builder().gameId(gameId).awayTeamScore(AWAY_SCORE).homeTeamScore(HOME_SCORE).build();
        //when
        GameDto result = unit.apply(game);
        //then
        assertEquals(HOME_SCORE, result.getHomeTeamScore());
        assertEquals(AWAY_SCORE, result.getAwayTeamScore());
        assertEquals(HOME_TEAM, result.getHomeTeam());
        assertEquals(AWAY_TEAM, result.getAwayTeam());
    }
}

package org.example.scoreboard.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {


    @Test
    void shouldUpdateScores() {
        //when
        Game unit = Game.builder().build();
        unit.updateTeamScores(1, 2);
        //then
        assertEquals(1, unit.getHomeTeamScore());
        assertEquals(2, unit.getAwayTeamScore());
    }

    @Test
    void shouldAddScores() {
        //when
        Game unit = Game.builder().homeTeamScore(3).awayTeamScore(2).build();
        //then
        assertEquals(5, unit.getScoreSum());
    }
}

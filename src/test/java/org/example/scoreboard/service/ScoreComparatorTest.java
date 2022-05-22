package org.example.scoreboard.service;

import org.example.scoreboard.model.Game;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreComparatorTest {

    private final ScoreComparator unit = new ScoreComparator();

    @Test
    void shouldReturnPositiveWhenGame1StartedBeforeGame2ForEqualScore() {
        //given
        Game game1 = Game.builder().homeTeamScore(2).awayTeamScore(0).startTime(LocalDateTime.now()).build();
        Game game2 = Game.builder().homeTeamScore(2).awayTeamScore(0).startTime(LocalDateTime.now().plusMinutes(1L)).build();
        //when
        int result = unit.compare(game1, game2);
        //then
        assertEquals(1, result);
    }

    @Test
    void shouldReturnPositiveWhenGame1StartedBeforeGame2ForEqualSumScore() {
        //given
        Game game1 = Game.builder().homeTeamScore(2).awayTeamScore(0).startTime(LocalDateTime.now()).build();
        Game game2 = Game.builder().homeTeamScore(1).awayTeamScore(1).startTime(LocalDateTime.now().plusMinutes(1L)).build();
        //when
        int result = unit.compare(game1, game2);
        //then
        assertEquals(1, result);
    }

    @Test
    void shouldReturnNegativeWhenGame1ScoredHigherThanGame2() {
        //given
        Game game1 = Game.builder().homeTeamScore(3).awayTeamScore(0).build();
        Game game2 = Game.builder().homeTeamScore(2).awayTeamScore(0).build();
        //when
        int result = unit.compare(game1, game2);
        //then
        assertEquals(-1, result);
    }

    @Test
    void shouldReturnNegativeWhenGame1SumScoredHigherThanGame2() {
        //given
        Game game1 = Game.builder().homeTeamScore(3).awayTeamScore(4).build();
        Game game2 = Game.builder().homeTeamScore(5).awayTeamScore(0).build();
        //when
        int result = unit.compare(game1, game2);
        //then
        assertEquals(-1, result);
    }

    @Test
    void shouldReturnZeroWhenBothGamesStartedAtTheSameTimeWithTheSameScore() {
        //given
        Game game1 = Game.builder().homeTeamScore(1).awayTeamScore(1).startTime(LocalDateTime.MAX).build();
        Game game2 = Game.builder().homeTeamScore(1).awayTeamScore(1).startTime(LocalDateTime.MAX).build();
        //when
        int result = unit.compare(game1, game2);
        //then
        assertEquals(0, result);
    }
}

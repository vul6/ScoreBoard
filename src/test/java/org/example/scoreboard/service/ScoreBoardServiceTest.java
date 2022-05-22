package org.example.scoreboard.service;

import org.example.scoreboard.dto.GameDto;
import org.example.scoreboard.model.GameId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardServiceTest {

    private final ScoreBoardService unit = new ScoreBoardService();

    private static final String FRANCE = "France";
    private static final String ENGLAND = "England";

    @Test
    void shouldStartGameWith2TeamsAndInitialScore() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        //then
        GameId gameId = GameId.builder().homeTeam(FRANCE).awayTeam(ENGLAND).build();
        assertEquals(1, unit.getScoreBoard().size());
        assertEquals(FRANCE, unit.getScoreBoard().get(gameId).getGameId().getHomeTeam());
        assertEquals(ENGLAND, unit.getScoreBoard().get(gameId).getGameId().getAwayTeam());
        assertEquals(0, unit.getScoreBoard().get(gameId).getHomeTeamScore());
        assertEquals(0, unit.getScoreBoard().get(gameId).getAwayTeamScore());
    }

    @Test
    void shouldFailToStartAGameOfTeamsAlreadyPlaying() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        //then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> unit.startGame(FRANCE, ENGLAND));

        String expectedMessage = "At least one of provided teams is already involved in a different game";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldFailToStartAGameOfTeamWithNullName() {
        //when
        //then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> unit.startGame(FRANCE, null));

        String expectedMessage = "Team names can't be null or empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldStartGameWith2TeamsAndUpdateScore() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        unit.updateScore(FRANCE, ENGLAND, 1, 0);
        //then
        GameId gameId = GameId.builder().homeTeam(FRANCE).awayTeam(ENGLAND).build();
        assertEquals(1, unit.getScoreBoard().size());
        assertEquals(FRANCE, unit.getScoreBoard().get(gameId).getGameId().getHomeTeam());
        assertEquals(ENGLAND, unit.getScoreBoard().get(gameId).getGameId().getAwayTeam());
        assertEquals(1, unit.getScoreBoard().get(gameId).getHomeTeamScore());
        assertEquals(0, unit.getScoreBoard().get(gameId).getAwayTeamScore());
    }

    @Test
    void shouldFailToUpdateAGameScoreOfNotExistingGame() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        //then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> unit.updateScore(FRANCE, "Brazil", 1, 0));

        String expectedMessage = "Game with provided teams is not in play";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldFailToUpdateAGameScoreWithNegativeValue() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        //then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> unit.updateScore(FRANCE, ENGLAND, -1, 0));

        String expectedMessage = "Teams can't have a score below 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldStartGameAndFinishIt() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        unit.finishGame(FRANCE, ENGLAND);
        //then
        assertEquals(0, unit.getScoreBoard().size());
    }

    @Test
    void shouldStartGameAndFailUpdatingAfterFinishingIt() {
        //when
        unit.startGame(FRANCE, ENGLAND);
        unit.finishGame(FRANCE, ENGLAND);

        //then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> unit.updateScore(FRANCE, ENGLAND, 1, 0));

        String expectedMessage = "Game with provided teams is not in play";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getSummaryOfAllStartedGames() throws InterruptedException {
        //when
        unit.startGame("Mexico", "Canada");
        //Code executes too fast after being optimized within JVM. Sleep is needed so the start times of games aren't set as equal
        Thread.sleep(200);
        unit.startGame("Spain", "Brazil");
        Thread.sleep(200);
        unit.startGame("Germany", FRANCE);
        Thread.sleep(200);
        unit.startGame("Uruguay", "Italy");
        Thread.sleep(200);
        unit.startGame("Argentina", "Australia");

        unit.updateScore("Mexico", "Canada", 0 , 5);
        unit.updateScore("Spain", "Brazil", 10, 2);
        unit.updateScore("Germany", FRANCE, 2, 2);
        unit.updateScore("Uruguay", "Italy", 6, 6);
        unit.updateScore("Argentina", "Australia", 3 ,1);

        List<GameDto> summary = unit.getSummary();
        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Germany", summary.get(4).getHomeTeam());
    }
}

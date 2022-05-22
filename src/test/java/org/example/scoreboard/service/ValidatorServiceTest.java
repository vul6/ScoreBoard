package org.example.scoreboard.service;

import org.example.scoreboard.model.GameId;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorServiceTest {

    public static final String BRAZIL = "Brazil";
    public static final String CANADA = "Canada";
    Set<GameId> gameIds = new HashSet<>(List.of(GameId.builder().homeTeam(FRANCE).awayTeam(ENGLAND).build()));

    private static final String FRANCE = "France";
    private static final String ENGLAND = "England";


    @Test
    void shouldThrowExceptionValidateExistingIfTeamNameEmpty() {
        GameId gameId = GameId.builder().homeTeam("").awayTeam("").build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateExistingTeams(gameId, gameIds));

        String expectedMessage = "Team names can't be null or empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldThrowExceptionValidateExistingIfTeamNameNull() {
        GameId gameId = GameId.builder().build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateExistingTeams(gameId, gameIds));

        String expectedMessage = "Team names can't be null or empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowExceptionValidateExistingIfTeamNameInNotStartedGame() {
        GameId gameId = GameId.builder().homeTeam(CANADA).awayTeam(BRAZIL).build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateExistingTeams(gameId, gameIds));

        String expectedMessage = "Game with provided teams is not in play";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldNotThrowExceptionValidateExistingIfTeamNamesFilledAndTeamsInGame() {
        GameId gameId = GameId.builder().homeTeam(FRANCE).awayTeam(ENGLAND).build();

        assertDoesNotThrow(() -> ValidatorService.validateExistingTeams(gameId, gameIds));

    }

    @Test
    void shouldThrowExceptionValidateNewIfTeamNameInStartedGame() {
        GameId gameId = GameId.builder().awayTeam(ENGLAND).homeTeam(FRANCE).build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateNewTeams(gameId, gameIds));

        String expectedMessage = "At least one of provided teams is already involved in a different game";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void shouldNotThrowExceptionValidateNewIfTeamNamesFilledAndNewTeamsNotInGame() {
        GameId gameId = GameId.builder().homeTeam(BRAZIL).awayTeam(CANADA).build();

        assertDoesNotThrow(() -> ValidatorService.validateNewTeams(gameId, gameIds));

    }

    @Test
    void shouldThrowExceptionValidateNewIfTeamNameEmpty() {
        GameId gameId = GameId.builder().homeTeam("").awayTeam("").build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateNewTeams(gameId, gameIds));

        String expectedMessage = "Team names can't be null or empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldThrowExceptionValidateNewIfTeamNameNull() {
        GameId gameId = GameId.builder().build();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateNewTeams(gameId, gameIds));

        String expectedMessage = "Team names can't be null or empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowExceptionIfScoresNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ValidatorService.validateScores(-1, -1));

        String expectedMessage = "Teams can't have a score below 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

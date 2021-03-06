package org.example.scoreboard.service;

import lombok.NoArgsConstructor;
import org.example.scoreboard.model.GameId;

import java.util.Set;
@NoArgsConstructor
public class ValidatorService {

    public static void validateExistingTeams(GameId gameId, Set<GameId> gameIds) {
        validateTeamNamesNotNullOrEmpty(gameId.getHomeTeam(), gameId.getAwayTeam());
        if(!gameIds.contains(gameId)) {
            throw new IllegalArgumentException("Game with provided teams is not in play");
        }
    }

    public static void validateNewTeams(GameId gameId, Set<GameId> gameIds) {
        validateTeamNamesNotNullOrEmpty(gameId.getHomeTeam(), gameId.getAwayTeam());
        if (gameIds.stream().anyMatch(id -> id.getAwayTeam().equals(gameId.getAwayTeam()) || id.getAwayTeam().equals(gameId.getHomeTeam()) ||
                id.getHomeTeam().equals(gameId.getAwayTeam()) || id.getHomeTeam().equals(gameId.getHomeTeam()))) {
            throw new IllegalArgumentException("At least one of provided teams is already involved in a different game");
        }
    }

    private static void validateTeamNamesNotNullOrEmpty(String homeTeam, String awayTeam) {
        if(homeTeam == null || "".equals(homeTeam) || awayTeam == null || "".equals(awayTeam)) {
            throw new IllegalArgumentException("Team names can't be null or empty!");
        }
    }

    public static void validateScores(int homeTeamScore, int awayTeamScore) {
        if(homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Teams can't have a score below 0");
        }
    }
}

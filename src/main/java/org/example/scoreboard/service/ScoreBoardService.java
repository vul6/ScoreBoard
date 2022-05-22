package org.example.scoreboard.service;

import lombok.Getter;
import org.example.scoreboard.dto.GameDto;
import org.example.scoreboard.model.Game;
import org.example.scoreboard.model.GameId;
import org.example.scoreboard.model.TeamScore;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoardService {
    @Getter
    private Map<GameId, Game> scoreBoard;

    public void startGame(String homeTeam, String awayTeam) {
        if (this.scoreBoard == null) {
            scoreBoard = new HashMap<>();
        }
        GameId gameId = GameId.builder().home(homeTeam).away(awayTeam).build();
        ValidatorService.validateNewTeams(gameId, scoreBoard.keySet());
        Game game = Game.builder()
                .homeTeam(TeamScore.builder().teamName(homeTeam).score(0).build())
                .awayTeam(TeamScore.builder().teamName(awayTeam).score(0).build())
                .startTime(LocalDateTime.now())
                .build();

        this.scoreBoard.put(gameId, game);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
        GameId gameId = GameId.builder().home(homeTeam).away(awayTeam).build();
        ValidatorService.validateExistingTeams(gameId, scoreBoard.keySet());
        Game game = scoreBoard.get(gameId);
        ValidatorService.validateScores(homeTeamScore, awayTeamScore);
        game.updateTeamScores(homeTeamScore, awayTeamScore);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        GameId gameId = GameId.builder().home(homeTeam).away(awayTeam).build();
        ValidatorService.validateExistingTeams(gameId, scoreBoard.keySet());
        scoreBoard.remove(gameId);
    }

    public List<GameDto> getSummary() {
        Comparator<? super Game> scoreComparator = new ScoreComparator();
        GameConverter converter = new GameConverter();
        return scoreBoard.values().stream().sorted(scoreComparator).map(converter).collect(Collectors.toList());
    }
}

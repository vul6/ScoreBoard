package org.example.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
@Getter
public class Game {

    private final GameId gameId;
    private int homeTeamScore;
    private int awayTeamScore;
    private final LocalDateTime startTime;

    public void updateTeamScores(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore= awayTeamScore;
    }

    public int getScoreSum() {
        return homeTeamScore + awayTeamScore;
    }


}

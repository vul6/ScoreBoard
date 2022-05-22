package org.example.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
@Getter
public class Game {

    private final TeamScore homeTeam;
    private final TeamScore awayTeam;
    private final LocalDateTime startTime;

    public void updateTeamScores(int homeTeamScore, int awayTeam) {
        this.homeTeam.setScore(homeTeamScore);
        this.awayTeam.setScore(awayTeam);
    }

    public int getScoreSum() {
        return homeTeam.getScore() + awayTeam.getScore();
    }


}

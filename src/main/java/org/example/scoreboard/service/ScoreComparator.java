package org.example.scoreboard.service;

import org.example.scoreboard.model.Game;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Game> {
    @Override
    public int compare(Game game1, Game game2) {
        int scoreComparison = Integer.compare(game2.getScoreSum(), game1.getScoreSum());
        if(scoreComparison == 0) {
            return game2.getStartTime().compareTo(game1.getStartTime());
        }
        return scoreComparison;
    }
}

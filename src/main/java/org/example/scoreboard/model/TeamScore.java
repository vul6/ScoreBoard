package org.example.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class TeamScore {

    private final String teamName;
    @Setter
    private int score;

}

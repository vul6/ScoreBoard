package org.example.scoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
public final class GameId {
    private final String home;
    private final String away;

}

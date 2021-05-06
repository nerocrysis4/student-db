package com.game.leaderboard.model.audioModel;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Podcast {
    private Song song;
    private String host;
    private List<String> participants;

}

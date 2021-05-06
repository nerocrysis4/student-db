package com.game.leaderboard.model.audioModel;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Song {
    private int id;
    private String name;
    private int duration;
    private Date uploadDt;

}

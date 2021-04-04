package com.game.leaderboard.model.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "user")
public class UserCsvToJson {
    @Id
    private String uid;
    private int tx_coins;
    private int tx_gems;
    private int total_coins_after;
    private int total_gems_after;
    private String match_player;
    private String match_id;
    private String match_type;
    private String tx_detail;
    private String app_versioncode;
    private String time_stamp;
    private String leauge_data;
    private String league_coins;
    private Date match_start_time;
    private String bet_amount;


//    private int id;
//    private String name;
}

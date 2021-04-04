package com.game.leaderboard.model.outputModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.game.leaderboard.model.collection.User;
import com.game.leaderboard.model.mapper.UserCsvToJson;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;

@Data
public class UserRankOutputModel {
    private String id;
    private int txCoins;
    private int totalCoinsAfter;
    private Date timeStamp;
    private int rank;

    public static UserRankOutputModel mapUser(User inputModel){
        if(inputModel == null)
            return null;
        UserRankOutputModel user = new UserRankOutputModel();
        user.setId(inputModel.getId());
        user.setTimeStamp(inputModel.getTimeStamp());
        user.setTxCoins(inputModel.getTxCoins());
        user.setTotalCoinsAfter(inputModel.getTotalCoinsAfter());
        return user;
    }
}

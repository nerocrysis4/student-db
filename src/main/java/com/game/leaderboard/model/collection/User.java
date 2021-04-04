package com.game.leaderboard.model.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.leaderboard.model.mapper.UserCsvToJson;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Data
@Document(collection = "user")
public class User {
    @Id
    @JsonProperty("_id")
    private String id;
    private int txCoins;
    private int totalCoinsAfter;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date timeStamp;

    public static User mapUser(UserCsvToJson inputModel){
        if(inputModel == null)
            return null;
        User user = new User();
        user.setId(inputModel.getUid());
        try {
            user.setTimeStamp(getDate(inputModel.getTime_stamp()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setTxCoins(inputModel.getTx_coins());
        user.setTotalCoinsAfter(inputModel.getTotal_coins_after());

        return user;
    }

    private static Date getDate(String testDate) throws ParseException {
        if (testDate == null || testDate.isEmpty()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(testDate);
        return date;
    }

    public static List<User> mapUserList(List<UserCsvToJson> inputModel){
        List<User> userList = new ArrayList<>();
        inputModel.stream().forEach(obj -> userList.add(mapUser(obj)));
        return userList;
    }

}

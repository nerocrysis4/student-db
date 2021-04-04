package com.game.leaderboard.repo;

import com.game.leaderboard.model.collection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    public int getRankByScore(int coin) {
        Query query = new Query();
        query.addCriteria(Criteria.where("totalCoinsAfter").gt(coin));
        query.fields().include("_id");
        return mongoTemplate.find(query, User.class).size();
    }

}

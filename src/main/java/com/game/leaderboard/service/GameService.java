package com.game.leaderboard.service;

import com.game.leaderboard.model.outputModel.UserRankOutputModel;
import com.game.leaderboard.model.response.ResponseModel;
import com.game.leaderboard.model.collection.User;
import com.game.leaderboard.repo.GameRepo;
import com.game.leaderboard.service.impl.IGameService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class GameService implements IGameService {

    @Autowired
    GameRepo repository;

    public ResponseModel getUserRank(String userId) {
        User user = repository
                .getUserById(userId);

        int rank = repository.getRankByScore(user.getTotalCoinsAfter());
        UserRankOutputModel outputModel = UserRankOutputModel.mapUser(user);
        outputModel.setRank(rank);

        return new ResponseModel(outputModel, "data fetch successfully");
    }
}

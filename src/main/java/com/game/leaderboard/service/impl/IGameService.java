package com.game.leaderboard.service.impl;

import com.game.leaderboard.model.response.ResponseModel;

public interface IGameService {

    ResponseModel getUserRank(String userId);
}

package com.game.leaderboard.service.impl;

import com.game.leaderboard.model.inputModel.AudioInputModel;
import com.game.leaderboard.model.response.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface IGameService {

    ResponseModel getUserRank(String userId);

    ResponseModel upload(MultipartFile file, AudioInputModel inputModel);

    ResponseModel download(String type, String id);
}

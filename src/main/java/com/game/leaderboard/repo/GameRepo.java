package com.game.leaderboard.repo;

import com.game.leaderboard.model.collection.User;
import com.game.leaderboard.model.inputModel.AudioInputModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

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

    public String addVideo(AudioInputModel inputModel, MultipartFile file) throws IOException {
        DBObject metaData = mapMetaData(inputModel);
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(), inputModel.getSong().getName(), file.getContentType(), metaData);
        return id.toString();
    }

    public DBObject mapMetaData(AudioInputModel inputModel){
        DBObject metaData = new BasicDBObject();
        setAudioMetaData(inputModel, metaData);
        if(inputModel.getAudioFileType() == AudioInputModel.AudioFileType.Song){
            // nothing added
        } else if(inputModel.getAudioFileType() == AudioInputModel.AudioFileType.Podcast){
            metaData.put("host", inputModel.getHost());
            metaData.put("participants", inputModel.getParticipants());
        } else if(inputModel.getAudioFileType() == AudioInputModel.AudioFileType.Audiobook){
            metaData.put("author", inputModel.getAuthor());
            metaData.put("narrator", inputModel.getNarrator());
        }

        return  metaData;
    }

    private void setAudioMetaData(AudioInputModel inputModel, DBObject metaData) {
        metaData.put("type", inputModel.getAudioFileType().getAudioFileType());
        metaData.put("id", inputModel.getSong().getId());
        metaData.put("name", inputModel.getSong().getName());
        metaData.put("duration", inputModel.getSong().getDuration());
        metaData.put("uploadDt", inputModel.getSong().getUploadDt());
    }


}

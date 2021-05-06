package com.game.leaderboard.model.inputModel;

import com.game.leaderboard.model.audioModel.Song;
import com.game.leaderboard.model.collection.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AudioInputModel {
    public enum AudioFileType {
        Song("Song"),
        Podcast("Podcast"),
        Audiobook("Audiobook");

        private final String audioFileType;

        private AudioFileType(String type) {
            this.audioFileType = type;
        }
        public String getAudioFileType() {
            return this.audioFileType;
        }
    }
    private AudioFileType audioFileType;
    private Song song;
    private String host;
    private String author;
    private String narrator;
    private List<String> participants;

}

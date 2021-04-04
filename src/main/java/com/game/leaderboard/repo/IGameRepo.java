package com.game.leaderboard.repo;

import com.game.leaderboard.model.collection.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IGameRepo extends ReactiveCrudRepository<User, String> {


}

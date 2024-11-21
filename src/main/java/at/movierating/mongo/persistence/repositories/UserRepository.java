package at.movierating.mongo.persistence.repositories;

import at.movierating.mongo.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoUserRepository")
public interface UserRepository extends MongoRepository<User, Integer> {

}

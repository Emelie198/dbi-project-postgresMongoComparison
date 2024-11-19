package at.pmrc.mongo.persistence.repositories;

import at.pmrc.mongo.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("mongoUserRepository")
public interface UserRepository extends MongoRepository<User, Integer> {

}

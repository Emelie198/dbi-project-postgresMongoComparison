package at.pmrc.mongo.persistence.repositories;


import at.pmrc.mongo.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoReviewRepository")
public interface ReviewRepository extends MongoRepository<Review, Integer> {

}

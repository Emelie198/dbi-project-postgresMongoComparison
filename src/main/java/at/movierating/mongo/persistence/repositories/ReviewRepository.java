package at.movierating.mongo.persistence.repositories;


import at.movierating.mongo.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoReviewRepository")
public interface ReviewRepository extends MongoRepository<Review, Integer> {

}

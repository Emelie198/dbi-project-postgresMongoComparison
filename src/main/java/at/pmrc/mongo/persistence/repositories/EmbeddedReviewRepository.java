package at.pmrc.mongo.persistence.repositories;

import at.pmrc.mongo.model.EmbeddedReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmbeddedReviewRepository extends MongoRepository<EmbeddedReview, Integer> {
    // Additional query methods can be added if needed

    @Override
    Optional<EmbeddedReview> findById(Integer integer);
}

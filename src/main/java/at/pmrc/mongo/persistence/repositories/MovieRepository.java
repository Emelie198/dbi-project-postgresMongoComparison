package at.pmrc.mongo.persistence.repositories;

import at.pmrc.mongo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoMovieRepository")
public interface MovieRepository extends MongoRepository<Movie, Integer> {
}

package at.movierating.mongo.persistence.repositories;

import at.movierating.mongo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoMovieRepository")
public interface MovieRepository extends MongoRepository<Movie, Integer> {
}

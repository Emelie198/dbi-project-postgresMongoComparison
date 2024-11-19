package at.pmrc.postgres.persistence;

import at.pmrc.mongo.model.EmbeddedReview;
import at.pmrc.mongo.model.User;
import at.pmrc.mongo.persistence.repositories.EmbeddedReviewRepository;
import at.pmrc.mongo.persistence.repositories.MovieRepository;
import at.pmrc.mongo.persistence.repositories.ReviewRepository;
import at.pmrc.mongo.persistence.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("postgresSeeder")
public class Seeder implements CommandLineRunner {

    private @Autowired MovieRepository movieRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired ReviewRepository reviewRepository;

    @Override
    public void run(String... args) {
        //seed("movies.json", movieRepository, Movie.class, "small");
        seed("users.json", userRepository, User.class, "small");
        //seed("reviews.json", reviewRepository, Review.class, "small");
    }

    private <T> void seed(String jsonFile, CrudRepository<T, ?> repository, Class<T> entityClass, String size) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/database/migration/" + size + "/" + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: /database/migration/" + size + "/" + jsonFile);
            }

            List<T> records = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));
            repository.saveAll(records);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to seed data for " + entityClass.getSimpleName() + " from " + jsonFile);
        }
    }
}
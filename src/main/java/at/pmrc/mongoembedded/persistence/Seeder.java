package at.pmrc.mongoembedded.persistence;

import at.pmrc.mongo.model.EmbeddedReview;
import at.pmrc.mongo.persistence.repositories.EmbeddedReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("mongoEmbeddedSeeder")
public class Seeder implements CommandLineRunner {

    private @Autowired EmbeddedReviewRepository repository;

    @Override
    public void run(String... args) {
        seed("embedded.json", repository, EmbeddedReview.class, "small");
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
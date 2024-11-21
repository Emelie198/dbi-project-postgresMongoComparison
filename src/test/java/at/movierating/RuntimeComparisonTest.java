package at.movierating;

import at.movierating.mongo.model.*;

import at.movierating.mongo.model.EmbeddedReview;
import at.movierating.mongo.persistence.repositories.EmbeddedReviewRepository;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class RuntimeComparisonTest {

     private @Autowired at.movierating.mongo.persistence.repositories.UserRepository mongoUserRepository;
     private @Autowired at.movierating.mongo.persistence.Seeder mongoSeeder;

     private @Autowired at.movierating.mongoembedded.persistence.Seeder mongoEmbeddedSeeder;
     private @Autowired EmbeddedReviewRepository mongoEmbeddedReviewRepository;

     private @Autowired at.movierating.postgres.persistence.UserRepository postgresUserRepository;
     private @Autowired at.movierating.postgres.persistence.Seeder postgresSeeder;



    @Test
    public void performMongoTasks() {
        measureExecutionTime(() -> mongoSeeder.run("small"), "Seeding Mongo Database with 100 records");
        measureExecutionTime(() -> mongoSeeder.run("medium"), "Seeding Mongo Database with 1000 records");
        measureExecutionTime(() -> mongoSeeder.run("big"), "Seeding Mongo Database with 10000 records");

        measureExecutionTime(() -> mongoUserRepository.save(new at.movierating.mongo.model.User()), "MongoDB Create Operation");
        measureExecutionTime(() -> mongoUserRepository.findById(1), "MongoDB Read Operation");
        measureExecutionTime(() -> {
            User user = mongoUserRepository.findById(1).orElseThrow();
            user.setUserame("ichwillnichtmehr");
            mongoUserRepository.save(user);
        }, "MongoDB Update Operation");
        measureExecutionTime(() -> mongoUserRepository.deleteById(1), "MongoDB Delete Operation");
    }

    @Test
    public void performMongoEmbeddedTasks() {
        measureExecutionTime(() -> mongoEmbeddedSeeder.run("small"), "Seeding Embedded Mongo Database with 100 records");
        measureExecutionTime(() -> mongoEmbeddedSeeder.run("medium"), "Seeding Embedded Mongo Database with 1000 records");
        measureExecutionTime(() -> mongoEmbeddedSeeder.run("big"), "Seeding Embedded Mongo Database with 10000 records");

        measureExecutionTime(() -> mongoEmbeddedReviewRepository.save(new EmbeddedReview()), "Embedded MongoDB Create Operation");
        measureExecutionTime(() -> mongoEmbeddedReviewRepository.findById(1), "Embedded MongoDB Read Operation");
//        measureExecutionTime(() -> {
//            EmbeddedReview embeddedReview = mongoEmbeddedReviewRepository.findById(1).orElseThrow();
//            embeddedReview.setUsername("ichwillnichtmehr");
//            mongoEmbeddedReviewRepository.save(embeddedReview);
//        }, "Embedded MongoDB Update Operation");
        measureExecutionTime(() -> mongoEmbeddedReviewRepository.deleteById(1), "Embedded MongoDB Delete Operation");
    }

    @Test
    public void performPostgresTasks() {
        measureExecutionTime(() -> postgresSeeder.run("small"), "Seeding Postgres Database with 100 records");
        measureExecutionTime(() -> postgresSeeder.run("medium"), "Seeding Postgres Database with 1000 records");
        measureExecutionTime(() -> postgresSeeder.run("big"), "Seeding Postgres Database with 10000 records");

        measureExecutionTime(() -> postgresUserRepository.save(new at.movierating.postgres.model.User()), "Postgres Create Operation");
        measureExecutionTime(() -> postgresUserRepository.findById(1), "Postgres Read Operation");
        measureExecutionTime(() -> {
            at.movierating.postgres.model.User user = postgresUserRepository.findById(1).orElseThrow();
            user.setUserame("ichwillnichtmehr");
            postgresUserRepository.save(user);
        }, "Postgres Update Operation");
        measureExecutionTime(() -> postgresUserRepository.deleteById(1), "Postgres Delete Operation");
    }

    private void measureExecutionTime(Runnable task, String taskDescription) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();

        System.out.println(taskDescription + ": " + (endTime - startTime) + "ms");
    }
}

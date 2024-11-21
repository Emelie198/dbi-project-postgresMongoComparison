package at.movierating.mongo.model;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "reviews")
public class Review {

    @Id
    private int id;
    private int user_id;
    private int movie_id;
    private int rating;
    private String reviewDate;
}
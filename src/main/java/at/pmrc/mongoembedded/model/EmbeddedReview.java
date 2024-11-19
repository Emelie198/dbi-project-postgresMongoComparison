package at.pmrc.mongo.model;

import at.pmrc.mongoembedded.model.Review;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "embedded_reviews")
public class EmbeddedReview {

    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    private String created_at;
    private List<Review> reviews;
}
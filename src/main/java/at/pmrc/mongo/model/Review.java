package at.pmrc.mongo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import jakarta.persistence.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
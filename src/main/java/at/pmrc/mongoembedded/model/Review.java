package at.pmrc.mongoembedded.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Review {
    private String rating; //1-5
    private String reviewDate;
    private at.pmrc.mongoembedded.model.Movie movie;
}
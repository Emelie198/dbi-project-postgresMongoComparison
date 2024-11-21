package at.movierating.mongoembedded.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Review {
    private String rating; //1-5
    private String reviewDate;
    private at.movierating.mongoembedded.model.Movie movie;
}
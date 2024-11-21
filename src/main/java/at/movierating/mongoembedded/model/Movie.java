package at.movierating.mongoembedded.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Movie {
    private String title;
    private String genre;
    private String releaseDate;
    private String director;
}
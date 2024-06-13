package br.com.alura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Movie (@JsonProperty("poster_path") String posterPath,
                     @JsonProperty("release_date") LocalDate releaseDate,
                     String title,
                     @JsonProperty("vote_average") Double voteAverage) implements Content {
    @Override
    public String urlImage() {
        return posterPath;
    }

    @Override
    public String rating() {
        return String.format("%.2f", voteAverage);
    }

    @Override
    public String year() {
        return String.valueOf(releaseDate.getYear());
    }
}

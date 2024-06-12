package br.com.alura;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
                .orElseThrow(() -> new IllegalStateException("API_KEY not set as environment variable"));
        try {
            String rawMovies = new TmdbApiClient(apiKey).getTopRatedMovies();
            List<Movie> movies = new TmdbMovieJsonParser(rawMovies).parse();

            try (PrintWriter writer = new PrintWriter("index.html")) {
                HTMLGenerator generator = new HTMLGenerator(writer);
                generator.generate(movies);
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
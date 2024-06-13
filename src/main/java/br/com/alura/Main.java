package br.com.alura;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
                .orElseThrow(() -> new IllegalStateException("API_KEY not set as environment variable"));

        try (PrintWriter writer = new PrintWriter("index.html")) {
            String rawMovies = new TmdbApiClient(apiKey).getBody();
            List<Movie> movies = new TmdbMovieJsonParser(rawMovies).parse();
            new HTMLGenerator(writer).generate(movies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
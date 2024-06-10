package br.com.alura;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
                .orElseThrow(() -> new IllegalStateException("API_KEY not set as environment variable"));
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            try (HttpClient client = HttpClient.newBuilder().build()) {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());

                String body = response.body();
                String moviesArray = body.substring(body.indexOf("["), body.lastIndexOf("]") + 1);
                List<Movie> movies = mapper.readValue(moviesArray, new TypeReference<>() {});

                System.out.println(movies);
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
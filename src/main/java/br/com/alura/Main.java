package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
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
                System.out.println(response.body());
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
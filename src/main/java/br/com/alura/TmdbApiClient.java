package br.com.alura;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;

public class TmdbApiClient {
    private final String apiKey;

    public TmdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTopRatedMovies() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey))
                .header("Accept", "application/json")
                .GET()
                .build();

        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}

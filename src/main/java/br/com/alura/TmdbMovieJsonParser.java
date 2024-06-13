package br.com.alura;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class TmdbMovieJsonParser implements JsonParser {
    private static final ObjectMapper mapper;
    private final String json;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public TmdbMovieJsonParser(String json) {
        this.json = json;
    }

    @Override
    public List<Movie> parse() {
        String moviesArray = findMoviesArray();
        try {
            return mapper.readValue(moviesArray, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String findMoviesArray() {
        return json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);
    }
}

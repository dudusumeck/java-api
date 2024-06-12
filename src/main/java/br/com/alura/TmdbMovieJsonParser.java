package br.com.alura;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class TmdbMovieJsonParser {
    private static final ObjectMapper mapper;
    private final String json;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public TmdbMovieJsonParser(String json) {
        this.json = json;
    }

    public List<Movie> parse() throws JsonProcessingException {
        String moviesArray = getMoviesArray();
        return mapper.readValue(moviesArray, new TypeReference<>() {});
    }

    private String getMoviesArray() {
        return json.substring(json.indexOf("["), json.lastIndexOf("]") + 1);
    }
}

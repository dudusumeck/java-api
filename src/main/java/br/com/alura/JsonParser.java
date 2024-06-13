package br.com.alura;

import java.util.List;

public interface JsonParser {
    List<? extends Content> parse();
}

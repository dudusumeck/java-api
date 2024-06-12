package br.com.alura;

import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>");
        builder.append("<html lang=\"en\">");
        builder.append("<head>");
        builder.append("<meta charset=\"utf-8\">");
        builder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        builder.append("<title>Top-rated movies</title>");
        builder.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        builder.append("</head>");
        builder.append("<body>");
        builder.append("<div class=\"container text-center\">");
        builder.append("<div class=\"row\">");
        for (Movie movie : movies) {
            builder.append("<div class=\"card text-bg-secondary m-3\" style=\"max-width: 18rem;\">");
            builder.append("<img src=\"https://image.tmdb.org/t/p/w500").append(movie.posterPath()).append("\" class=\"card-img-top\" alt=\"").append(movie.title()).append(" poster\">");
            builder.append("<div class=\"card-body\">");
            builder.append("<h5 class=\"card-title\">").append(movie.title()).append("</h5>");
            builder.append("<p class=\"card-text\">Vote average: ").append(String.format("%.2f", movie.voteAverage())).append(" - Year: ").append(movie.releaseDate().getYear()).append("</p>");
            builder.append("</div>");
            builder.append("</div>");
        }
        builder.append("</div>");
        builder.append("</div>");
        builder.append("</body>");
        builder.append("</html>");

        writer.write(builder.toString());
    }
}

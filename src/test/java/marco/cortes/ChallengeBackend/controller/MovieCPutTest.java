package marco.cortes.ChallengeBackend.controller;

import marco.cortes.ChallengeBackend.entity.Genre;
import marco.cortes.ChallengeBackend.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieCPutTest extends AbstractTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL2xvZ2luIiwiZXhwIjoxNjU1Njc5MjUxfQ.WNXdJqKHnQSlqaOVFpcocE8wuCcTMhzij4uWPKOsAjQ";

    @Test
    void updateMovie() throws Exception {
        String uri = "/movies/2/update";
        Genre genre_1 = new Genre();
        genre_1.setId(1L);

        Movie movie_1 = new Movie();
        movie_1.setId(2L);
        movie_1.setTitle("League of legends");
        movie_1.setImage("Image 1");
        movie_1.setGenre(genre_1);
        movie_1.setScore(2);

        mvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(mapToJson(movie_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());
    }

    @Test
    void updateMovieNotFound() throws Exception {
        String uri = "/movies/9999999/update";
        Genre genre_1 = new Genre();
        genre_1.setId(999999L);
        genre_1.setName("Genre 1");
        genre_1.setImage("Genre Image 1");

        Movie movie_1 = new Movie();
        movie_1.setId(1L);
        movie_1.setTitle("League of legends 2");
        movie_1.setImage("Image 1");
        movie_1.setScore(2);
        movie_1.setGenre(genre_1);

        mvc.perform(MockMvcRequestBuilders.put(uri)
                        .content(mapToJson(movie_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }
}

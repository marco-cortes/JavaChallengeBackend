package marco.cortes.ChallengeBackend.controller;

import marco.cortes.ChallengeBackend.entity.Genre;
import marco.cortes.ChallengeBackend.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieAPostTest extends AbstractTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL2xvZ2luIiwiZXhwIjoxNjU1Njc5MjUxfQ.WNXdJqKHnQSlqaOVFpcocE8wuCcTMhzij4uWPKOsAjQ";

    @Test
    void addGenres() throws Exception {
        String uri = "/movies/genre/add";
        Genre genre_1 = new Genre();
        genre_1.setName("Genre 1");
        genre_1.setImage("Genre Image 1");

        Genre genre_2 = new Genre();
        genre_2.setName("Genre 2");
        genre_2.setImage("Genre Image 2");

        Genre genre_3 = new Genre();
        genre_3.setName("Genre 3");
        genre_3.setImage("Genre Image 3");

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(genre_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(genre_2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(genre_3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre.id").exists());
    }

    @Test
    void addMovies() throws Exception {
        String uri = "/movies/add";

        Genre genre_1 = new Genre();
        genre_1.setId(1L);

        Genre genre_2 = new Genre();
        genre_2.setId(2L);

        Movie movie_1 = new Movie();
        movie_1.setTitle("Title 1");
        movie_1.setImage("Image 1");
        movie_1.setGenre(genre_1);
        movie_1.setScore(5);

        Movie movie_2 = new Movie();
        movie_2.setTitle("Title 2");
        movie_2.setImage("Image 2");
        movie_2.setGenre(genre_1);
        movie_2.setScore(1);

        Movie movie_3 = new Movie();
        movie_3.setTitle("Title 3");
        movie_3.setImage("Image 3");
        movie_3.setGenre(genre_1);
        movie_3.setScore(2);

        Movie movie_4 = new Movie();
        movie_4.setTitle("Title 4");
        movie_4.setImage("Image 4");
        movie_4.setGenre(genre_2);
        movie_4.setScore(3);

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(movie_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(movie_2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(movie_3))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(movie_4))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());
    }

    @Test
    void addWrongMovie() throws Exception {
        String uri = "/movies/add";
        Genre genre = new Genre();
        genre.setId(2L);

        Movie movie = new Movie();
        movie.setTitle("");
        movie.setImage("");
        movie.setGenre(null);
        movie.setScore(-5);

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("false"));

    }
}

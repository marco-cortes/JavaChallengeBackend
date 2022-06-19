package marco.cortes.ChallengeBackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieBGetTest extends AbstractTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL2xvZ2luIiwiZXhwIjoxNjU1Njc5MjUxfQ.WNXdJqKHnQSlqaOVFpcocE8wuCcTMhzij4uWPKOsAjQ";

    @Test
    void getMovies() throws Exception {
        String uri = "/movies";
        mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies").exists());

        mvc.perform(MockMvcRequestBuilders.get(uri+"?name=movie_1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies").exists());

        mvc.perform(MockMvcRequestBuilders.get(uri+"?genre=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies").exists());

        mvc.perform(MockMvcRequestBuilders.get(uri+"?order=ASC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies").exists());
    }

    @Test
    void movieById() throws Exception {
        String uri = "/movies/1";
        mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.id").exists());
    }

    @Test
    void movieByIdNotFound() throws Exception {
        String uri = "/movies/9999999";
        mvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("false"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }
}

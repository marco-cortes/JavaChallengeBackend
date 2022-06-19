package marco.cortes.ChallengeBackend.controller;

import marco.cortes.ChallengeBackend.entity.Personage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CharacterAPostTest extends AbstractTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL2xvZ2luIiwiZXhwIjoxNjU1Njc5MjUxfQ.WNXdJqKHnQSlqaOVFpcocE8wuCcTMhzij4uWPKOsAjQ";

    @Test
    void addCharacters() throws Exception {
        String uri = "/characters/add";

        Personage personage_1 = new Personage();
        personage_1.setName("Character 1");
        personage_1.setImage("Image 1");
        personage_1.setWeight(61.32f);
        personage_1.setHistory("History 1");
        personage_1.setAge(17);

        Personage personage_2 = new Personage();
        personage_2.setName("Character 2");
        personage_2.setImage("Image 2");
        personage_2.setWeight(52.2f);
        personage_2.setHistory("History 2");
        personage_2.setAge(25);

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(personage_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.character.id").exists());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(personage_2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.character.id").exists());

    }

    @Test
    void addWrongCharacter() throws Exception {
        String uri = "/characters/add";
        Personage personage_1 = new Personage();

        mvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(mapToJson(personage_1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ok").value("false"));
    }
}

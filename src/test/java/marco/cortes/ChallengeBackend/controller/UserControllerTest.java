package marco.cortes.ChallengeBackend.controller;

import marco.cortes.ChallengeBackend.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void register() throws Exception {
        String uri = "/auth/register";
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test1234");
        String inputJson = super.mapToJson(user);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}
package com.openclassrooms.bobapp.controller;

import com.openclassrooms.bobapp.model.Joke;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnJsonWithJoke() throws Exception {
        mockMvc.perform(get("/api/joke")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke").exists())
                .andExpect(jsonPath("$.response").exists());
    }
}

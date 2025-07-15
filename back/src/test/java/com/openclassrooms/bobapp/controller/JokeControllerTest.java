package com.openclassrooms.bobapp.controller;

import com.openclassrooms.bobapp.model.Joke;
import com.openclassrooms.bobapp.service.JokeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class JokeControllerTest {

    @Test
    void shouldReturnRandomJoke() {
        // GIVEN
        JokeService mockService = Mockito.mock(JokeService.class);
        Joke expected = new Joke("Test joke", "Test response");
        Mockito.when(mockService.getRandomJoke()).thenReturn(expected);

        JokeController controller = new JokeController(mockService);

        // WHEN
        ResponseEntity<?> response = controller.getRandomJokes();

        // THEN
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(expected);
    }
}

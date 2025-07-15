package com.openclassrooms.bobapp.data;

import com.openclassrooms.bobapp.model.Joke;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonReaderTest {

    @Test
    void shouldLoadJokesFromJson() {
        JsonReader reader = JsonReader.getInstance();
        List<Joke> jokes = reader.getJokes();

        assertThat(jokes).isNotEmpty();
        assertThat(jokes.get(0).getJoke()).isNotEmpty();
        assertThat(jokes.get(0).getResponse()).isNotEmpty();
    }
}

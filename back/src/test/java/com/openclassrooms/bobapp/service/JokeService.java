package com.openclassrooms.bobapp.service;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class JokeService {

    private final List<Joke> jokes;

    public JokeService() {
        this.jokes = JsonReader.getInstance().getJokes();
    }

    public Joke getRandomJoke() {
        if (jokes.isEmpty()) {
            return new Joke("No joke available", "¯\\_(ツ)_/¯");
        }
        Random rand = new Random();
        return jokes.get(rand.nextInt(jokes.size()));
    }
}

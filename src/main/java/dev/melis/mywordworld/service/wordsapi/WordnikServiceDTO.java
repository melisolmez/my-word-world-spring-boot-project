package dev.melis.mywordworld.service.wordsapi;

import lombok.Data;

@Data
public class WordnikServiceDTO {
    private String word;
    private String definition;
    private String example;
    private String pronunciation;

    public WordnikServiceDTO setWord(String word) {
        this.word = word;
        return this;
    }
    public WordnikServiceDTO setDefinition(String definition) {
        this.definition = definition;
        return this;
    }
    public WordnikServiceDTO setExample(String example) {
        this.example = example;
        return this;
    }
    public WordnikServiceDTO setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
        return this;
    }
}

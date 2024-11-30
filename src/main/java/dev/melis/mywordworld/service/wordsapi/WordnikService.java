package dev.melis.mywordworld.service.wordsapi;

public interface WordnikService {

    String getWordDefinition(String word);
    String getWordExample(String word);
    String getWordPronunciationAudio(String word);
    void getAndSaveWordInformation(String word);
}

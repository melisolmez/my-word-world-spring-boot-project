package dev.melis.mywordworld.service.wordsapi;

import dev.melis.mywordworld.config.UserSession;

public interface WordnikService {

    String getWordDefinition(String word);
    String getWordExample(String word);
    String getWordPronunciationAudio(String word);
    WordnikServiceDTO getAndSaveWordInformation(String word, UserSession userSession);
}

package dev.melis.mywordworld.service.wordsapi;

import java.util.List;

import dev.melis.mywordworld.config.UserSession;

public interface WordService {
    List<WordnikServiceDTO> listAllWords(UserSession userSession);
    WordnikServiceDTO getWord(String word,UserSession userSession);
    boolean deleteWord(Long id);
}

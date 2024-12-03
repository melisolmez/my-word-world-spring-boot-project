package dev.melis.mywordworld.service.wordsapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.melis.mywordworld.config.UserSession;
import dev.melis.mywordworld.model.Word;
import dev.melis.mywordworld.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<WordnikServiceDTO> listAllWords(UserSession userSession) {
        if (userSession == null || userSession.id() == null) {
            throw new IllegalArgumentException("User session is invalid");
        }
        List<Word> words = wordRepository.findByUserId(userSession.id());

        return words.stream()
                .map(word -> new WordnikServiceDTO()
                        .setWord(word.getWord())
                        .setDefinition(word.getDefinition())
                        .setExample(word.getExample())
                        .setPronunciation(word.getAudioFileName()))
                        .collect(Collectors.toList());
    }

    @Override
    public WordnikServiceDTO getWord(String word,UserSession userSession) {
        if (userSession == null || userSession.id() == null) {
            throw new IllegalArgumentException("User session is invalid");
        }

        Optional<Word> dbWord = wordRepository.findByWordAndUserId(word,userSession.id());
        if(dbWord.isPresent()) {
            Word wordFromDb = dbWord.get();
            return new WordnikServiceDTO()
                    .setWord(wordFromDb.getWord())
                    .setDefinition(wordFromDb.getDefinition())
                    .setExample(wordFromDb.getExample())
                    .setPronunciation(wordFromDb.getAudioFileName());

        }

        return null;
    }

    @Override
    public boolean deleteWord(Long id) {
        Optional <Word> dbWord = wordRepository.findById(id);
        if(dbWord.isPresent()){
            wordRepository.delete(dbWord.get());
            return true;
        }
        return false;
    }
}

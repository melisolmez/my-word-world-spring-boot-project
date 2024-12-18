package dev.melis.mywordworld.service.wordsapi;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.melis.mywordworld.config.UserSession;
import dev.melis.mywordworld.model.Word;
import dev.melis.mywordworld.repository.WordRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WordnikServiceImpl implements WordnikService {

    @Value("${API_KEY}")
    private String apiKey;

    private final WebClient webClient;
    private final WordRepository repository;
    private final ObjectMapper mapper;

    public WordnikServiceImpl(WebClient webClient, WordRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.webClient = webClient;
        this.mapper = objectMapper;
    }

    @Override
    public String getWordDefinition(String word) {
        try{
            String definitionUrl = String.format("/word.json/%s/definitions?limit=1&api_key=%s", word, apiKey);
            String response = getApiResponse(definitionUrl);

            JsonNode jsonNode = mapper.readTree(response);
            if(jsonNode.isArray() && !jsonNode.isEmpty()) {
                return jsonNode.get(0).get("text").asText();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "No definition found.";
    }

    @Override
    public String getWordExample(String word) {
        try{
            String exampleUrl = String.format("/word.json/%s/examples?limit=1&api_key=%s", word, apiKey);
            String response = getApiResponse(exampleUrl);

            JsonNode jsonNode = mapper.readTree(response);
            if(jsonNode.has("examples") && jsonNode.get("examples").isArray() && !jsonNode.get("examples").isEmpty()) {
                return jsonNode.get("examples").get(0).get("text").asText();
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "No example found.";
    }

    @Override
    public String getWordPronunciationAudio(String word) {
        try{
            String pronunciationUrl = String.format("/word.json/%s/audio?limit=1&api_key=%s", word, apiKey);
            String response = getApiResponse(pronunciationUrl);

            JsonNode rootNode = mapper.readTree(response);

            if (rootNode.isArray() && !rootNode.isEmpty()) {
                return rootNode.get(0).get("fileUrl").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Audio not available for this word.";
    }

    @Override
    public WordnikServiceDTO getAndSaveWordInformation(String word, UserSession userSession) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Word must not be null or empty");
        }
        if (userSession == null || userSession.id() == null) {
            throw new IllegalArgumentException("User session is invalid");
        }

        Optional<Word> exist = repository.findByWord(word);
        if(exist.isPresent()) {
            var existingWord = exist.get();
            return buildWordnikServiceDTO(existingWord.getWord(),existingWord.getDefinition(),existingWord.getExample(),existingWord.getAudioFileName());

        }
        String wordDefinition = getWordDefinition(word);
        String wordExample = getWordExample(word);
        String wordPronunciation = getWordPronunciationAudio(word);

        if (wordDefinition == null || wordExample == null || wordPronunciation == null) {
            throw new RuntimeException("Failed to fetch word information");
        }

        var words = new Word();
        words.setWord(word);
        words.setDefinition(wordDefinition);
        words.setExample(wordExample);
        words.setAudioFileName(wordPronunciation);
        words.setUserId(userSession.id());

        repository.save(words);
        return buildWordnikServiceDTO(word,wordDefinition,wordExample,wordPronunciation);

    }

    private WordnikServiceDTO buildWordnikServiceDTO(String word, String definition, String example, String pronunciation) {
        return new WordnikServiceDTO()
                .setWord(word)
                .setDefinition(definition)
                .setExample(example)
                .setPronunciation(pronunciation);
    }

    private String getApiResponse(String url){
        return  webClient.get()
                         .uri(url)
                         .retrieve()
                         .bodyToMono(String.class)
                         .block();
    }
}

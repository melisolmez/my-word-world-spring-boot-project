package dev.melis.mywordworld.controller.apicontroller;

import dev.melis.mywordworld.config.UserSession;
import dev.melis.mywordworld.service.wordsapi.WordnikService;
import dev.melis.mywordworld.service.wordsapi.WordnikServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/word")
public class WordsController {

    private final WordnikService wordnikService;

    public WordsController(WordnikService wordnikService) {
        this.wordnikService = wordnikService;
    }
    @GetMapping("/information")
    public ResponseEntity<?> getWordInformation(@RequestParam String word, UserSession userSession) {
        try{
            WordnikServiceDTO wordInfo = wordnikService.getAndSaveWordInformation(word,userSession);
            return ResponseEntity.ok(wordInfo);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch or save word information: " + e.getMessage());
        }
    }

    @GetMapping("/definition")
    public ResponseEntity<?> getWordDefinition(@RequestParam String word) {
        try{
            String wordDefinition = wordnikService.getWordDefinition(word);
            return ResponseEntity.ok(wordDefinition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed get word definition: " + e.getMessage());
        }
    }

    @GetMapping("/example")
    public ResponseEntity<?> getWordExample(@RequestParam String word) {
        try{
            String wordExample = wordnikService.getWordExample(word);
            return ResponseEntity.ok(wordExample);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed get word example: " + e.getMessage());
        }
    }

    @GetMapping("/audiofile")
    public ResponseEntity<?> getAudiofile(@RequestParam String word) {
        try{
            String filePronunciation = wordnikService.getWordPronunciationAudio(word);
            return ResponseEntity.ok(filePronunciation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed get word audio file: " + e.getMessage());
        }
    }

}

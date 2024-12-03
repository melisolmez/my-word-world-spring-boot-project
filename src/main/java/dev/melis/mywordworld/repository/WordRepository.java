package dev.melis.mywordworld.repository;

import java.util.List;
import java.util.Optional;

import dev.melis.mywordworld.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByWordAndUserId(String word,Long userId);
    List<Word> findByUserId(Long userId);

    Optional<Word> findByWord(String word);
}

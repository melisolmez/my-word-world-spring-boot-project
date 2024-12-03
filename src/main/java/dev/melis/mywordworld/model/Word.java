package dev.melis.mywordworld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
public class Word {
    @Id
    private Long id;
    private String word;
    private String meaning;
    private String example;
    private String definition;
    private String story;

    @JoinColumn(name = "user_id")
    private Long userId;
    private String audioFileName;

}

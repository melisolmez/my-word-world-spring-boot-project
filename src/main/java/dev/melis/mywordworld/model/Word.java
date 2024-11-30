package dev.melis.mywordworld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    private byte[] audioData;

    private String audioMimeType;
    private String audioFileName;

}

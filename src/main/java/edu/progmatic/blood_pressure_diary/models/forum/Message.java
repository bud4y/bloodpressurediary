package edu.progmatic.blood_pressure_diary.models.forum;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String author;
    public String text;
    public LocalDateTime dateTime;
    boolean isDeleted;

    private Long messageTopic;

    public Message(String text, String author, LocalDateTime dateTime, boolean isDeleted, Long messageTopic) {
        this.text = text;
        this.author = author;
        this.dateTime = dateTime;
        this.isDeleted = isDeleted;
        this.messageTopic = messageTopic;
    }

}

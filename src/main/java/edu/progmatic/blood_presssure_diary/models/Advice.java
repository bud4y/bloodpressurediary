package edu.progmatic.blood_presssure_diary.models;

import javax.persistence.*;

@Entity
public class Advice {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private String text;
//    @OneToOne
//    MeasurementDetails measurementDetails;
    public Advice(){

    }

    public Advice(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

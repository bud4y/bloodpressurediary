package edu.progmatic.bpdiary.model.evaluation;

import javax.persistence.*;

@Entity
public class Evaluate {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String text;

//    @OneToOne
//    MeasurementDetails measurementDetails;
    public Evaluate(){

    }

    public Evaluate(String text) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

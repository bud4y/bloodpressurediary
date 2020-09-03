package com.progmatic.bpdiary.model.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BloodPressureValue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int minAge;
    private int maxAge;
    private String properValue;
    private String minValue;
    private String maxValue;

    public BloodPressureValue(int minAge, int maxAge, String properValue, String minValue, String maxValue) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.properValue = properValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "BloodPressureValue{" +
                "id=" + id +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", properValue='" + properValue + '\'' +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                '}';
    }
}

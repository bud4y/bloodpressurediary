package edu.progmatic.blood_pressure_diary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class MeasureDTO {
    @Min(65)
    @Max(220)
    private int systolic;
    @Min(70)
    @Max(130)
    private int diastolic;
    @Min(40)
    @Max(220)
    private int pulse;
    @Min(1)
    @Max(4)
    private Long conditionId;
}

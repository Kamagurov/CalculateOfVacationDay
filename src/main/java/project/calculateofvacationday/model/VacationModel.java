package project.calculateofvacationday.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class VacationModel {

    private LocalDate localDate;

    private double avgSalaryFpr12Months;

    private int vacationDays;
}

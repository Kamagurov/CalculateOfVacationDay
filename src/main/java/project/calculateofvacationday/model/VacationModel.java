package project.calculateofvacationday.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationModel {

    private LocalDate startDate;

    private double avgSalaryFpr12Months;

    private int vacationDays;
}

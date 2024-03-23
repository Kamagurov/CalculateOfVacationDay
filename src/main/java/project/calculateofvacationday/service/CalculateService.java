package project.calculateofvacationday.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import project.calculateofvacationday.model.PublicHoliday;
import project.calculateofvacationday.model.VacationModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

@Service
@Data
public class CalculateService {

    private final List<PublicHoliday> publicHolidays;

    public double getCalcOfVacationDay(VacationModel vacationModel) {
        LocalDate startDate = vacationModel.getLocalDate().minusYears(1);
        int period = vacationModel.getVacationDays();

        long businessDays = getBusinessDaysWithHolidays(startDate, vacationModel.getLocalDate());
        double avgSalaryPerDay = (vacationModel.getAvgSalaryFpr12Months() * period) / businessDays;

        return avgSalaryPerDay * vacationModel.getVacationDays();
    }

    private long getBusinessDaysWithHolidays(LocalDate startDate, LocalDate endDate) {
        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
                .filter(this::isBusinessDay)
                .filter(date -> !isPublicHoliday(date))
                .count();
    }

    private boolean isBusinessDay(LocalDate date) {
        return date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    private boolean isPublicHoliday(LocalDate date) {
        return publicHolidays.stream()
                .anyMatch(holiday -> LocalDate.parse(holiday.date).isEqual(date));
    }
}

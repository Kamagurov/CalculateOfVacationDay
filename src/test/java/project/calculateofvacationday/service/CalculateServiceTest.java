package project.calculateofvacationday.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import project.calculateofvacationday.model.VacationModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

    @Test
    public void testGetCalcOfVacationDay() throws ReflectiveOperationException {
        CalculateService calculateService = new CalculateService();
        VacationModel vacationModel = new VacationModel(LocalDate.now(), 55000.0, 14);

        Method privateMethod = CalculateService.class.getDeclaredMethod("getBusinessDaysWithHolidays", LocalDate.class, LocalDate.class);
        privateMethod.setAccessible(true);

        long businessDays = (long) privateMethod.invoke(calculateService, vacationModel.getStartDate().minusYears(1), vacationModel.getStartDate());

        double avgSalaryPerDay = vacationModel.getAvgSalaryFpr12Months() * vacationModel.getVacationDays() / businessDays;
        double expectedResult = avgSalaryPerDay * vacationModel.getVacationDays();

        Field privateField = CalculateService.class.getDeclaredField("publicHolidays");
        privateField.setAccessible(true);
        if(privateField.get(calculateService) == null){
            privateField.set(calculateService, new ArrayList<LocalDate>());
        }

        double result = calculateService.getCalcOfVacationDay(vacationModel);

        assertEquals(expectedResult, result, 0.0);
    }
}
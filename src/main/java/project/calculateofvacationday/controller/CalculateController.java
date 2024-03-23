package project.calculateofvacationday.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.calculateofvacationday.model.VacationModel;
import project.calculateofvacationday.service.CalculateService;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CalculateController {

    private final CalculateService service;

    private final ObjectMapper mapper;

    @SneakyThrows
    @GetMapping(path = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCalcOfVacationDay(@RequestBody String message) {
        VacationModel model = mapper.readValue(message, VacationModel.class);
        return new ResponseEntity<>(String.valueOf(service.getCalcOfVacationDay(model)), HttpStatus.OK);
    }
}

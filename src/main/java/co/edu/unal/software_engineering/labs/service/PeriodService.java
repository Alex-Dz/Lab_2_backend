package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.Period;
import co.edu.unal.software_engineering.labs.repository.PeriodRepository;
import org.springframework.stereotype.Service;

@Service
public class PeriodService {

    private PeriodRepository periodRepository;

    public PeriodService(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    public Period findById(Integer id) {
        return periodRepository.findById(id).orElse(null);
    }

    public Period findLast() {
        return periodRepository.findTopByIdIsNotNullOrderByPeriodNameDesc();
    }
}

package co.edu.unal.software_engineering.labs.repository;

import co.edu.unal.software_engineering.labs.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Integer>{
    Period findTopByIdIsNotNullOrderByPeriodNameDesc();
}

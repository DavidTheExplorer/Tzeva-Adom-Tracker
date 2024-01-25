package dte.tzevaadomtracker.repositories.specifications;

import dte.tzevaadomtracker.alert.AlertEntity;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalTime.MIDNIGHT;
import static java.time.Month.JANUARY;

public class AlertSpecifications
{
    private static final LocalDateTime YEAR_TWO_THOUSAND = LocalDate.of(2000, JANUARY, 1)
            .atTime(MIDNIGHT); //used when the start date isn't specified(using LocalDateTime.MIN is impossible because it's out of the DB's range)

    public static Specification<AlertEntity> happenedAt(String region)
    {
        return (root, querty, criteriaBuilder) ->
        {
            if(region == null)
                return criteriaBuilder.conjunction();

            return criteriaBuilder.like(root.get("region"), "%" + region + "%");
        };
    }

    public static Specification<AlertEntity> wasBetween(LocalDateTime start, LocalDateTime end)
    {
        return (root, query, criteriaBuilder) ->
        {
            LocalDateTime realStart = (start == null) ? YEAR_TWO_THOUSAND : start;
            LocalDateTime realEnd = (end == null) ? LocalDateTime.now() : end;

            Path<LocalDateTime> date = root.get("date");

            return criteriaBuilder.and(
                    criteriaBuilder.greaterThan(date, realStart),
                    criteriaBuilder.lessThanOrEqualTo(date, realEnd));
        };
    }
}

package dte.tzevaadomtracker.services;

import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.dto.requests.AlertHistoryRequest;
import dte.tzevaadomtracker.repositories.AlertRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static dte.tzevaadomtracker.repositories.specifications.AlertSpecifications.happenedAt;
import static dte.tzevaadomtracker.repositories.specifications.AlertSpecifications.wasBetween;

@Service
public class AlertService
{
    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository)
    {
        this.alertRepository = alertRepository;
    }

    public void track(AlertEntity alertEntity)
    {
        this.alertRepository.save(alertEntity);
    }

    public List<AlertEntity> findBy(AlertHistoryRequest request)
    {
        Specification<AlertEntity> specification = Specification.allOf(happenedAt(request.region()), wasBetween(request.startDate(), request.endDate()));

        return this.alertRepository.findAll(specification, Pageable.ofSize(1000)).toList();
    }
}



package dte.tzevaadomtracker.services;

import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.dto.mappers.AlertMapper;
import dte.tzevaadomtracker.dto.requests.AlertHistoryRequest;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.repositories.AlertRepository;
import org.springframework.context.event.EventListener;
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
    private final AlertMapper alertMapper;

    public AlertService(AlertRepository alertRepository, AlertMapper alertMapper)
    {
        this.alertRepository = alertRepository;
        this.alertMapper = alertMapper;
    }

    @EventListener
    public void trackAlert(TzevaAdomEvent event)
    {
        AlertEntity alertEntity = this.alertMapper.toEntity(event.getSource());

        this.alertRepository.save(alertEntity);
    }

    public List<AlertEntity> findBy(AlertHistoryRequest request)
    {
        Specification<AlertEntity> specification = Specification.allOf(happenedAt(request.region()), wasBetween(request.startDate(), request.endDate()));

        return this.alertRepository.findAll(specification, Pageable.ofSize(1000)).toList();
    }
}



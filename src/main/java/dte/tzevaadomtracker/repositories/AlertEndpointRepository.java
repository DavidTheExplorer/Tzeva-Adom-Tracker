package dte.tzevaadomtracker.repositories;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertEndpointRepository extends JpaRepository<AlertEndpoint, Long>
{
    boolean existsByUrl(String url);
}

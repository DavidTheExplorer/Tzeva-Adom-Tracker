package dte.tzevaadomtracker.repositories;

import dte.tzevaadomtracker.alert.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Long>, JpaSpecificationExecutor<AlertEntity>
{

}

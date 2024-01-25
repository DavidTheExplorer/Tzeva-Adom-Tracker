package dte.tzevaadomtracker.dto.mappers;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.dto.AlertDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertMapper
{
    AlertDTO toDTO(AlertEntity alert);

    AlertEntity toEntity(Alert alert);
}

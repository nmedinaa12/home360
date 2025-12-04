package MicroservicioVistas.infrastructure.mappers;

import MicroservicioVistas.domain.model.TimeSlotModel;
import MicroservicioVistas.infrastructure.entities.TimeSlotEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeSlotEntityMapper {

    TimeSlotModel toModel(TimeSlotEntity timeSlotEntity);

    TimeSlotEntity toEntity(TimeSlotModel timeSlotModel);
}

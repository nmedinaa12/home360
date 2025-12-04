package MicroservicioVistas.application.mappers;

import MicroservicioVistas.application.dto.request.SaveTimeSlotRequest;
import MicroservicioVistas.application.dto.response.TimeSlotResponse;
import MicroservicioVistas.domain.model.TimeSlotModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TimeSlotDtoMapper {

    TimeSlotModel requestToModel(SaveTimeSlotRequest request);

    TimeSlotResponse modelToResponse(TimeSlotModel model);

}

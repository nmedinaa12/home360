package MicroservicioVistas.application.mappers;

import MicroservicioVistas.application.dto.request.SaveTimeSlotRequest;
import MicroservicioVistas.application.dto.response.TimeSlotResponse;
import MicroservicioVistas.domain.model.TimeSlotModel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T20:01:55-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TimeSlotDtoMapperImpl implements TimeSlotDtoMapper {

    @Override
    public TimeSlotModel requestToModel(SaveTimeSlotRequest request) {
        if ( request == null ) {
            return null;
        }

        TimeSlotModel timeSlotModel = new TimeSlotModel();

        timeSlotModel.setHomeId( request.homeId() );
        timeSlotModel.setStartTime( request.startTime() );
        timeSlotModel.setEndTime( request.endTime() );

        return timeSlotModel;
    }

    @Override
    public TimeSlotResponse modelToResponse(TimeSlotModel model) {
        if ( model == null ) {
            return null;
        }

        Long id = null;
        Long sellerId = null;
        Long homeId = null;
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        id = model.getId();
        sellerId = model.getSellerId();
        homeId = model.getHomeId();
        startTime = model.getStartTime();
        endTime = model.getEndTime();

        TimeSlotResponse timeSlotResponse = new TimeSlotResponse( id, sellerId, homeId, startTime, endTime );

        return timeSlotResponse;
    }
}

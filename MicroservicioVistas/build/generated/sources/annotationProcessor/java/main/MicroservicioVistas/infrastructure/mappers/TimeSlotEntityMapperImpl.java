package MicroservicioVistas.infrastructure.mappers;

import MicroservicioVistas.domain.model.TimeSlotModel;
import MicroservicioVistas.infrastructure.entities.TimeSlotEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T20:01:55-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class TimeSlotEntityMapperImpl implements TimeSlotEntityMapper {

    @Override
    public TimeSlotModel toModel(TimeSlotEntity timeSlotEntity) {
        if ( timeSlotEntity == null ) {
            return null;
        }

        TimeSlotModel timeSlotModel = new TimeSlotModel();

        timeSlotModel.setId( timeSlotEntity.getId() );
        timeSlotModel.setSellerId( timeSlotEntity.getSellerId() );
        timeSlotModel.setHomeId( timeSlotEntity.getHomeId() );
        timeSlotModel.setStartTime( timeSlotEntity.getStartTime() );
        timeSlotModel.setEndTime( timeSlotEntity.getEndTime() );

        return timeSlotModel;
    }

    @Override
    public TimeSlotEntity toEntity(TimeSlotModel timeSlotModel) {
        if ( timeSlotModel == null ) {
            return null;
        }

        TimeSlotEntity timeSlotEntity = new TimeSlotEntity();

        timeSlotEntity.setId( timeSlotModel.getId() );
        timeSlotEntity.setSellerId( timeSlotModel.getSellerId() );
        timeSlotEntity.setHomeId( timeSlotModel.getHomeId() );
        timeSlotEntity.setStartTime( timeSlotModel.getStartTime() );
        timeSlotEntity.setEndTime( timeSlotModel.getEndTime() );

        return timeSlotEntity;
    }
}

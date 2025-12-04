package MicroservicioCasas.infrastructure.mappers;

import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.infrastructure.entities.LocationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T11:41:37-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class LocationEntityMapperImpl implements LocationEntityMapper {

    @Override
    public LocationEntity modelToEntity(LocationModel locationModel) {
        if ( locationModel == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setId( locationModel.getId() );
        locationEntity.setCity( locationModel.getCity() );
        locationEntity.setDepartment( locationModel.getDepartment() );
        locationEntity.setDescription( locationModel.getDescription() );

        return locationEntity;
    }

    @Override
    public LocationModel entityToModel(LocationEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Long id = null;
        String city = null;
        String department = null;
        String description = null;

        id = categoryEntity.getId();
        city = categoryEntity.getCity();
        department = categoryEntity.getDepartment();
        description = categoryEntity.getDescription();

        LocationModel locationModel = new LocationModel( id, city, department, description );

        return locationModel;
    }

    @Override
    public List<LocationModel> entityListToModelList(List<LocationEntity> locations) {
        if ( locations == null ) {
            return null;
        }

        List<LocationModel> list = new ArrayList<LocationModel>( locations.size() );
        for ( LocationEntity locationEntity : locations ) {
            list.add( entityToModel( locationEntity ) );
        }

        return list;
    }
}

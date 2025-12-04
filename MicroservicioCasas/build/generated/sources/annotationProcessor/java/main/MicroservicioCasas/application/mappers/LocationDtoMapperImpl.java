package MicroservicioCasas.application.mappers;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.LocationResponse;
import MicroservicioCasas.domain.model.LocationModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T11:41:36-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class LocationDtoMapperImpl implements LocationDtoMapper {

    @Override
    public LocationModel requestToModel(SaveLocationRequest saveLocationRequest) {
        if ( saveLocationRequest == null ) {
            return null;
        }

        String city = null;
        String department = null;
        String description = null;

        city = saveLocationRequest.city();
        department = saveLocationRequest.department();
        description = saveLocationRequest.description();

        Long id = null;

        LocationModel locationModel = new LocationModel( id, city, department, description );

        return locationModel;
    }

    @Override
    public List<LocationResponse> modelListToResponseList(List<LocationModel> locations) {
        if ( locations == null ) {
            return null;
        }

        List<LocationResponse> list = new ArrayList<LocationResponse>( locations.size() );
        for ( LocationModel locationModel : locations ) {
            list.add( locationModelToLocationResponse( locationModel ) );
        }

        return list;
    }

    protected LocationResponse locationModelToLocationResponse(LocationModel locationModel) {
        if ( locationModel == null ) {
            return null;
        }

        Long id = null;
        String city = null;
        String department = null;
        String description = null;

        id = locationModel.getId();
        city = locationModel.getCity();
        department = locationModel.getDepartment();
        description = locationModel.getDescription();

        LocationResponse locationResponse = new LocationResponse( id, city, department, description );

        return locationResponse;
    }
}

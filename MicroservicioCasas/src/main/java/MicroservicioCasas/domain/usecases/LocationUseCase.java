package MicroservicioCasas.domain.usecases;


import MicroservicioCasas.domain.exceptions.LocationAlreadyExistsException;
import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.domain.ports.in.LocationServicePort;
import MicroservicioCasas.domain.ports.out.LocationPersistencePort;

import java.util.List;


public class LocationUseCase implements LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;


    public LocationUseCase(LocationPersistencePort locationPersistencePort){
        this.locationPersistencePort = locationPersistencePort;
    }


    @Override
    public void save(LocationModel locationModel) {
        LocationModel existingLocation = locationPersistencePort.findByDepartmentAndCity(
                locationModel.getDepartment(),
                locationModel.getCity()
        );

        if (existingLocation != null) {
            throw new LocationAlreadyExistsException();
        }
        locationPersistencePort.save(locationModel);
    }

    @Override
    public List<LocationModel> searchLocations(String searchText, Integer page, Integer size, boolean orderAsc) {

        return locationPersistencePort.searchByText(searchText, page, size, orderAsc);
    }

}

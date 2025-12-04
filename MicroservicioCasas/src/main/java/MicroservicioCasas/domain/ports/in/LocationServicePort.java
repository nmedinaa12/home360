package MicroservicioCasas.domain.ports.in;

import MicroservicioCasas.domain.model.LocationModel;

import java.util.List;


public interface LocationServicePort {
    void save(LocationModel locationModel);
    List<LocationModel> searchLocations(String searchText, Integer page, Integer size, boolean orderAsc);
}
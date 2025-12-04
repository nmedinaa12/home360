package MicroservicioCasas.domain.ports.out;

import MicroservicioCasas.domain.model.LocationModel;

import java.util.List;


public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    LocationModel findByDepartmentAndCity(String department, String city);
    List<LocationModel> searchByText(String searchText, Integer page, Integer size, boolean orderAsc);
}

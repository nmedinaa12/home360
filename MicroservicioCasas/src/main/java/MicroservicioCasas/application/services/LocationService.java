package MicroservicioCasas.application.services;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.LocationResponse;
import MicroservicioCasas.application.dto.response.SaveLocationResponse;

import java.util.List;

public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);
    List<LocationResponse> getLocations(String searchText, Integer page, Integer size, boolean orderAsc);
}

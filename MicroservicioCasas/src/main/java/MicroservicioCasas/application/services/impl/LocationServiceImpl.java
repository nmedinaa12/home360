package MicroservicioCasas.application.services.impl;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.LocationResponse;
import MicroservicioCasas.application.dto.response.SaveLocationResponse;
import MicroservicioCasas.application.mappers.LocationDtoMapper;
import MicroservicioCasas.application.services.LocationService;
import MicroservicioCasas.configurations.utils.Constants;
import MicroservicioCasas.domain.ports.in.LocationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationServicePort locationServicePort;
    private final LocationDtoMapper locationDtoMapper;

    @Override
    public SaveLocationResponse save(SaveLocationRequest request) {
        locationServicePort.save(locationDtoMapper.requestToModel(request));
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<LocationResponse> getLocations(String searchText, Integer page, Integer size, boolean orderAsc) {
        return locationDtoMapper.modelListToResponseList(locationServicePort.searchLocations(searchText, page, size, orderAsc));
    }
}

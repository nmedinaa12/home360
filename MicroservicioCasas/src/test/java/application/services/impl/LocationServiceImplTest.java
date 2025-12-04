package application.services.impl;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.SaveLocationResponse;
import MicroservicioCasas.application.mappers.LocationDtoMapper;
import MicroservicioCasas.application.services.impl.LocationServiceImpl;
import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.domain.ports.in.LocationServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    private LocationServicePort locationServicePort;

    @Mock
    private LocationDtoMapper locationDtoMapper;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    void save_Successful() {
        // Arrange
        SaveLocationRequest request = new SaveLocationRequest("Medellin", "Antioquia", "Beautiful city");
        LocationModel model = new LocationModel(null, "Medellin", "Antioquia", "Beautiful city");

        when(locationDtoMapper.requestToModel(request)).thenReturn(model);
        doNothing().when(locationServicePort).save(model);

        // Act
        SaveLocationResponse response = locationService.save(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.time());
        assertEquals("Location created successfully.", response.message());
        verify(locationServicePort).save(model);
        verify(locationDtoMapper).requestToModel(request);
    }
}
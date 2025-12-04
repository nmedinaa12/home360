package infrastructure.endpoints.rest;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.SaveLocationResponse;
import MicroservicioCasas.application.services.LocationService;
import MicroservicioCasas.infrastructure.endpoints.rest.LocationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;


    @Test
    void save_Successful_ReturnsCreatedStatus() {
        // Arrange
        SaveLocationRequest request = new SaveLocationRequest("Cartagena", "Bolivar", "Tourist city");
        SaveLocationResponse response = new SaveLocationResponse("Location saved", null);
        when(locationService.save(request)).thenReturn(response);

        // Act
        ResponseEntity<SaveLocationResponse> result = locationController.save(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(locationService).save(request);
    }
}
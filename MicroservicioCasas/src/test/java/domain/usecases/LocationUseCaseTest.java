package domain.usecases;

import MicroservicioCasas.domain.exceptions.LocationAlreadyExistsException;
import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.domain.ports.out.LocationPersistencePort;
import MicroservicioCasas.domain.usecases.LocationUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;



    @Test
    void save_NewLocation_Success() {
        // Arrange
        LocationModel model = new LocationModel(null, "Cali", "Valle", "Nice city");
        when(locationPersistencePort.findByDepartmentAndCity("Valle", "Cali")).thenReturn(null);

        // Act
        locationUseCase.save(model);

        // Assert
        verify(locationPersistencePort).save(model);
    }

    @Test
    void save_ExistingLocation_ThrowsException() {
        // Arrange
        LocationModel model = new LocationModel(null, "Cali", "Valle", "Nice city");
        when(locationPersistencePort.findByDepartmentAndCity("Valle", "Cali"))
                .thenReturn(new LocationModel(1L, "Cali", "Valle", "Nice city"));

        // Act & Assert
        assertThrows(LocationAlreadyExistsException.class, () -> locationUseCase.save(model));
        verify(locationPersistencePort, never()).save(any());
    }
}
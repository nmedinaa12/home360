package domain.model;

import MicroservicioCasas.domain.exceptions.DescriptionDepMaxSizeExceededException;
import MicroservicioCasas.domain.exceptions.NameMaxSizeExceededException;
import MicroservicioCasas.domain.model.LocationModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationModelTest {

    @Test
    void constructor_ValidParameters_Success() {
        LocationModel model = new LocationModel(1L, "Bogota", "Cundinamarca", "Capital city");
        assertEquals(1L, model.getId());
        assertEquals("Bogota", model.getCity());
        assertEquals("Cundinamarca", model.getDepartment());
        assertEquals("Capital city", model.getDescription());
    }

    @Test
    void constructor_CityTooLong_ThrowsException() {
        String longCity = "a".repeat(51);
        assertThrows(NameMaxSizeExceededException.class, () ->
                new LocationModel(1L, longCity, "Dept", "Desc"));
    }

    @Test
    void constructor_DepartmentTooLong_ThrowsException() {
        String longDept = "a".repeat(51);
        assertThrows(NameMaxSizeExceededException.class, () ->
                new LocationModel(1L, "City", longDept, "Desc"));
    }

    @Test
    void constructor_DescriptionTooLong_ThrowsException() {
        String longDesc = "a".repeat(121);
        assertThrows(DescriptionDepMaxSizeExceededException.class, () ->
                new LocationModel(1L, "City", "Dept", longDesc));
    }
}
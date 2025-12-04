package domain.model;

import MicroservicioCasas.domain.exceptions.DescriptionMaxSizeExceededException;
import MicroservicioCasas.domain.exceptions.NameMaxSizeExceededException;
import MicroservicioCasas.domain.model.CategoryModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryModelTest {

    @Test
    void shouldCreateCategoryModelWithValidData() {
        // Arrange
        Long id = 1L;
        String name = "Valid Name";
        String description = "Valid Description";

        // Act
        CategoryModel model = new CategoryModel(id, name, description);

        // Assert
        assertEquals(id, model.getId());
        assertEquals(name, model.getName());
        assertEquals(description, model.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        Long id = 1L;
        String description = "Valid Description";

        // Act & Assert
        assertThrows(NullPointerException.class, () ->
                new CategoryModel(id, null, description));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        // Arrange
        Long id = 1L;
        String name = "Valid Name";

        // Act & Assert
        assertThrows(NullPointerException.class, () ->
                new CategoryModel(id, name, null));
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxSize() {
        // Arrange
        Long id = 1L;
        String longName = "A".repeat(51);
        String description = "Valid Description";

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class, () ->
                new CategoryModel(id, longName, description));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxSize() {
        // Arrange
        Long id = 1L;
        String name = "Valid Name";
        String longDescription = "A".repeat(91);

        // Act & Assert
        assertThrows(DescriptionMaxSizeExceededException.class, () ->
                new CategoryModel(id, name, longDescription));
    }

    @Test
    void shouldUpdateNameWithValidValue() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Old Name", "Description");
        String newName = "New Name";

        // Act
        model.setName(newName);

        // Assert
        assertEquals(newName, model.getName());
    }

    @Test
    void shouldThrowExceptionWhenUpdatedNameIsNull() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Name", "Description");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> model.setName(null));
    }

    @Test
    void shouldThrowExceptionWhenUpdatedNameExceedsMaxSize() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Name", "Description");
        String longName = "A".repeat(51);

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class, () -> model.setName(longName));
    }

    @Test
    void shouldUpdateDescriptionWithValidValue() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Name", "Old Description");
        String newDescription = "New Description";

        // Act
        model.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, model.getDescription());
    }

    @Test
    void shouldThrowExceptionWhenUpdatedDescriptionIsNull() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Name", "Description");

        // Act & Assert
        assertThrows(NullPointerException.class, () -> model.setDescription(null));
    }

    @Test
    void shouldThrowExceptionWhenUpdatedDescriptionExceedsMaxSize() {
        // Arrange
        CategoryModel model = new CategoryModel(1L, "Name", "Description");
        String longDescription = "A".repeat(91);

        // Act & Assert
        assertThrows(DescriptionMaxSizeExceededException.class, () ->
                model.setDescription(longDescription));
    }
}
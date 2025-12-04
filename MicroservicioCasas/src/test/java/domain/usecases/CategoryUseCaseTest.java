package domain.usecases;

import MicroservicioCasas.domain.exceptions.CategoryAlreadyExistsException;
import MicroservicioCasas.domain.exceptions.PageNumberLessThanZero;
import MicroservicioCasas.domain.exceptions.PageSizeLessThanOne;
import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.domain.ports.out.CategoryPersistencePort;
import MicroservicioCasas.domain.usecases.CategoryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void shouldSaveNewCategorySuccessfully() {
        // Arrange
        CategoryModel model = new CategoryModel(null, "New Category", "Description");
        when(categoryPersistencePort.getCategoryByName(model.getName())).thenReturn(null);

        // Act
        categoryUseCase.save(model);

        // Assert
        verify(categoryPersistencePort).getCategoryByName(model.getName());
        verify(categoryPersistencePort).save(model);
    }

    @Test
    void shouldThrowExceptionWhenCategoryExists() {
        // Arrange
        CategoryModel existingModel = new CategoryModel(1L, "Existing Category", "Description");
        CategoryModel newModel = new CategoryModel(null, "Existing Category", "New Description");

        when(categoryPersistencePort.getCategoryByName(newModel.getName()))
                .thenReturn(existingModel);

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () ->
                categoryUseCase.save(newModel));

        verify(categoryPersistencePort).getCategoryByName(newModel.getName());
        verify(categoryPersistencePort, never()).save(any());
    }

    @Test
    void shouldGetCategoriesSuccessfully() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean orderAsc = true;

        List<CategoryModel> expectedCategories = Arrays.asList(
                new CategoryModel(1L, "Cat1", "Desc1"),
                new CategoryModel(2L, "Cat2", "Desc2")
        );

        when(categoryPersistencePort.getCategories(page, size, orderAsc))
                .thenReturn(expectedCategories);

        // Act
        List<CategoryModel> result = categoryUseCase.getCategories(page, size, orderAsc);

        // Assert
        verify(categoryPersistencePort).getCategories(page, size, orderAsc);
        assertNotNull(result);
        assertEquals(expectedCategories.size(), result.size());
        assertEquals(expectedCategories.get(0).getName(), result.get(0).getName());
        assertEquals(expectedCategories.get(1).getName(), result.get(1).getName());
    }

    @Test
    void shouldThrowPageNumberLessThanZeroWhenPageIsNegative() {
        // Arrange
        Integer page = -1;
        Integer size = 10;
        boolean orderAsc = true;

        // Act & Assert
        assertThrows(PageNumberLessThanZero.class, () ->
                categoryUseCase.getCategories(page, size, orderAsc));

        verify(categoryPersistencePort, never()).getCategories(anyInt(), anyInt(), anyBoolean());
    }

    @Test
    void shouldThrowPageSizeLessThanOneWhenSizeIsZero() {
        // Arrange
        Integer page = 0;
        Integer size = 0;
        boolean orderAsc = false;

        // Act & Assert
        assertThrows(PageSizeLessThanOne.class, () ->
                categoryUseCase.getCategories(page, size, orderAsc));

        verify(categoryPersistencePort, never()).getCategories(anyInt(), anyInt(), anyBoolean());
    }

    @Test
    void shouldThrowPageSizeLessThanOneWhenSizeIsNegative() {
        // Arrange
        Integer page = 0;
        Integer size = -1;
        boolean orderAsc = false;

        // Act & Assert
        assertThrows(PageSizeLessThanOne.class, () ->
                categoryUseCase.getCategories(page, size, orderAsc));

        verify(categoryPersistencePort, never()).getCategories(anyInt(), anyInt(), anyBoolean());
    }
}
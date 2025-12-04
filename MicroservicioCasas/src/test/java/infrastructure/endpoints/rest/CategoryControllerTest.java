package infrastructure.endpoints.rest;

import MicroservicioCasas.application.dto.request.SaveCategoryRequest;
import MicroservicioCasas.application.dto.response.CategoryResponse;
import MicroservicioCasas.application.dto.response.PaginatedCategoryResponse;
import MicroservicioCasas.application.dto.response.SaveCategoryResponse;
import MicroservicioCasas.application.services.CategoryService;
import MicroservicioCasas.infrastructure.endpoints.rest.CategoryController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void shouldCreateCategorySuccessfully() {
        // Arrange
        SaveCategoryRequest request = new SaveCategoryRequest("Test Name", "Test Description");
        SaveCategoryResponse expectedResponse = new SaveCategoryResponse("Success", LocalDateTime.now());

        when(categoryService.save(request)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<SaveCategoryResponse> response = categoryController.save(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(categoryService).save(request);
    }

    @Test
    void shouldReturnAllCategoriesSuccessfully() {
        // Arrange
        int page = 1;
        int size = 5;
        boolean orderAsc = true;

        List<CategoryResponse> mockCategories = List.of(
                new CategoryResponse(1L, "Category 1", "Description 1"),
                new CategoryResponse(2L, "Category 2", "Description 2")
        );

        when(categoryService.getCategories(page, size, orderAsc)).thenReturn(mockCategories);

        // Act
        ResponseEntity<PaginatedCategoryResponse> response = categoryController.getAllCategories(page, size, orderAsc);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(page, response.getBody().page());
        assertEquals(size, response.getBody().size());
        assertEquals(mockCategories, response.getBody().categories());
    }
}
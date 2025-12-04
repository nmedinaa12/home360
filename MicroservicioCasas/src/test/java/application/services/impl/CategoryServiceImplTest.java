package application.services.impl;

import MicroservicioCasas.application.dto.request.SaveCategoryRequest;
import MicroservicioCasas.application.dto.response.CategoryResponse;
import MicroservicioCasas.application.dto.response.SaveCategoryResponse;
import MicroservicioCasas.application.mappers.CategoryDtoMapper;
import MicroservicioCasas.application.services.impl.CategoryServiceImpl;
import MicroservicioCasas.configurations.utils.Constants;
import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.domain.ports.in.CategoryServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryDtoMapper categoryDtoMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void shouldSaveCategorySuccessfully() {
        // Arrange
        SaveCategoryRequest request = new SaveCategoryRequest("Test Name", "Test Description");
        CategoryModel model = new CategoryModel(null, "Test Name", "Test Description");

        when(categoryDtoMapper.requestToModel(request)).thenReturn(model);

        // Act
        SaveCategoryResponse response = categoryService.save(request);

        // Assert
        verify(categoryDtoMapper).requestToModel(request);
        verify(categoryServicePort).save(model);

        assertEquals(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, response.message());
        assertNotNull(response.time());
    }

    @Test
    void shouldGetCategoriesSuccessfully() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean orderAsc = true;

        List<CategoryModel> categoryModels = Arrays.asList(
                new CategoryModel(1L, "Cat1", "Desc1"),
                new CategoryModel(2L, "Cat2", "Desc2")
        );

        List<CategoryResponse> expectedResponse = Arrays.asList(
                new CategoryResponse(1L, "Cat1", "Desc1"),
                new CategoryResponse(2L, "Cat2", "Desc2")
        );

        when(categoryServicePort.getCategories(page, size, orderAsc)).thenReturn(categoryModels);
        when(categoryDtoMapper.modelListToResponseList(categoryModels)).thenReturn(expectedResponse);

        // Act
        List<CategoryResponse> actualResponse = categoryService.getCategories(page, size, orderAsc);

        // Assert
        verify(categoryServicePort).getCategories(page, size, orderAsc);
        verify(categoryDtoMapper).modelListToResponseList(categoryModels);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.size(), actualResponse.size());
        assertEquals(expectedResponse.get(0).id(), actualResponse.get(0).id());
        assertEquals(expectedResponse.get(0).name(), actualResponse.get(0).name());
        assertEquals(expectedResponse.get(0).description(), actualResponse.get(0).description());
        assertEquals(expectedResponse.get(1).id(), actualResponse.get(1).id());
        assertEquals(expectedResponse.get(1).name(), actualResponse.get(1).name());
        assertEquals(expectedResponse.get(1).description(), actualResponse.get(1).description());
    }
}
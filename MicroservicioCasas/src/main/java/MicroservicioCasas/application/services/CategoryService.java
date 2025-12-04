package MicroservicioCasas.application.services;

import MicroservicioCasas.application.dto.request.SaveCategoryRequest;
import MicroservicioCasas.application.dto.response.CategoryResponse;
import MicroservicioCasas.application.dto.response.SaveCategoryResponse;

import java.util.List;


public interface CategoryService {
    SaveCategoryResponse save(SaveCategoryRequest request);
    List<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
}

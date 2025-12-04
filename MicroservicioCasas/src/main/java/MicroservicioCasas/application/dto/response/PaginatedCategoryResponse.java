package MicroservicioCasas.application.dto.response;

import java.util.List;

public record PaginatedCategoryResponse(
        Integer page,
        Integer size,
        List<CategoryResponse> categories
) {
}
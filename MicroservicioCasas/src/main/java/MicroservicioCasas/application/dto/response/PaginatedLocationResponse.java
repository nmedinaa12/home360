package MicroservicioCasas.application.dto.response;

import java.util.List;

public record PaginatedLocationResponse(
        Integer page,
        Integer size,
        List<LocationResponse> locations
) {
}
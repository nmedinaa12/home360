package MicroservicioCasas.infrastructure.endpoints.rest;

import MicroservicioCasas.application.dto.request.SaveLocationRequest;
import MicroservicioCasas.application.dto.response.*;
import MicroservicioCasas.application.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
@Tag(name = "Locations", description = "Create and search locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/")
    @Operation(
            summary = "Create Location",
            description = "Create a location with city, department, and description",
            tags = {"Create Locations"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requires a JSON with city, department, and description",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveLocationRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Location created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveLocationResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<SaveLocationResponse> save(@RequestBody SaveLocationRequest saveLocationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.save(saveLocationRequest));
    }

    @GetMapping("/")
    @Operation(
            summary = "Search Locations",
            description = "Retrieve a paginated list of locations based on search text",
            tags = {"Search Locations"},
            parameters = {
                    @Parameter(name = "searchText", description = "Text to search for in location names", required = true, in = ParameterIn.QUERY),
                    @Parameter(name = "page", description = "Page number (0-based index)", required = true, in = ParameterIn.QUERY),
                    @Parameter(name = "size", description = "Number of locations per page", required = true, in = ParameterIn.QUERY),
                    @Parameter(name = "orderAsc", description = "Sort order: true = ascending, false = descending", required = true, in = ParameterIn.QUERY)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Paginated list of locations retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PaginatedLocationResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<PaginatedLocationResponse> searchLocations(
            @RequestParam String searchText,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam boolean orderAsc) {

        List<LocationResponse> locations = locationService.getLocations(searchText, page, size, orderAsc);
        PaginatedLocationResponse response = new PaginatedLocationResponse(page, size, locations);

        return ResponseEntity.ok(response);
    }
}

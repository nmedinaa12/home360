package MicroservicioCasas.infrastructure.endpoints.rest;

import MicroservicioCasas.application.dto.request.SaveCategoryRequest;
import MicroservicioCasas.application.dto.response.CategoryResponse;
import MicroservicioCasas.application.dto.response.PaginatedCategoryResponse;
import MicroservicioCasas.application.dto.response.SaveCategoryResponse;
import MicroservicioCasas.application.services.CategoryService;
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
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Caregory", description = "Create and search categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    @Operation(
            summary = "Create Category",
            description = "Create category, name, description, auto-incrementing id",
            tags = {"Create Categories"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requires a json with name and description",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveCategoryRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Category created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SaveCategoryResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<SaveCategoryResponse> save(@RequestBody SaveCategoryRequest saveCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(saveCategoryRequest));
    }

    @GetMapping("/")
    @Operation(
            summary = "Get All Categories",
            description = "Retrieve a paginated list of categories with optional ordering",
            tags = {"Get Categories"},
            parameters = {
                    @Parameter(name = "page", description = "Page number (0-based index)", required = true, in = ParameterIn.QUERY),
                    @Parameter(name = "size", description = "Number of categories per page", required = true, in = ParameterIn.QUERY),
                    @Parameter(name = "orderAsc", description = "Sort order: true = ascending, false = descending", required = true, in = ParameterIn.QUERY)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Paginated list of categories retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PaginatedCategoryResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<PaginatedCategoryResponse> getAllCategories(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam boolean orderAsc) {

        List<CategoryResponse> categories = categoryService.getCategories(page, size, orderAsc);
        PaginatedCategoryResponse response = new PaginatedCategoryResponse(page, size, categories);

        return ResponseEntity.ok(response);
    }
}
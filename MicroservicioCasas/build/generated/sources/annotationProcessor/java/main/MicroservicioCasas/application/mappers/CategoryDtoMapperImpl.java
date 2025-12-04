package MicroservicioCasas.application.mappers;

import MicroservicioCasas.application.dto.request.SaveCategoryRequest;
import MicroservicioCasas.application.dto.response.CategoryResponse;
import MicroservicioCasas.domain.model.CategoryModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T11:41:37-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CategoryDtoMapperImpl implements CategoryDtoMapper {

    @Override
    public CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest) {
        if ( saveCategoryRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = saveCategoryRequest.name();
        description = saveCategoryRequest.description();

        Long id = null;

        CategoryModel categoryModel = new CategoryModel( id, name, description );

        return categoryModel;
    }

    @Override
    public CategoryResponse modelToResponse(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = categoryModel.getId();
        name = categoryModel.getName();
        description = categoryModel.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, name, description );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> modelListToResponseList(List<CategoryModel> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( CategoryModel categoryModel : categories ) {
            list.add( modelToResponse( categoryModel ) );
        }

        return list;
    }
}

package MicroservicioCasas.infrastructure.mappers;

import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.infrastructure.entities.CategoryEntity;
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
public class CategoryEntityMapperImpl implements CategoryEntityMapper {

    @Override
    public CategoryEntity modelToEntity(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( categoryModel.getId() );
        categoryEntity.setName( categoryModel.getName() );
        categoryEntity.setDescription( categoryModel.getDescription() );

        return categoryEntity;
    }

    @Override
    public CategoryModel entityToModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        String name = null;
        String description = null;
        Long id = null;

        name = categoryEntity.getName();
        description = categoryEntity.getDescription();
        id = categoryEntity.getId();

        CategoryModel categoryModel = new CategoryModel( id, name, description );

        return categoryModel;
    }

    @Override
    public List<CategoryModel> entityListToModelList(List<CategoryEntity> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryModel> list = new ArrayList<CategoryModel>( categories.size() );
        for ( CategoryEntity categoryEntity : categories ) {
            list.add( entityToModel( categoryEntity ) );
        }

        return list;
    }
}

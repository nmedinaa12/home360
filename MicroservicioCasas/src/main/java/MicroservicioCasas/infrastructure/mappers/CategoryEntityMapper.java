package MicroservicioCasas.infrastructure.mappers;

import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity modelToEntity(CategoryModel categoryModel);
    CategoryModel entityToModel(CategoryEntity categoryEntity);
    List<CategoryModel> entityListToModelList(List<CategoryEntity> categories);
}

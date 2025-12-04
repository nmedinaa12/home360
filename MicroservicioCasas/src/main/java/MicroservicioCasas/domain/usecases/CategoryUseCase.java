package MicroservicioCasas.domain.usecases;

import MicroservicioCasas.domain.exceptions.CategoryAlreadyExistsException;
import MicroservicioCasas.domain.exceptions.PageNumberLessThanZero;
import MicroservicioCasas.domain.exceptions.PageSizeLessThanOne;
import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.domain.ports.in.CategoryServicePort;
import MicroservicioCasas.domain.ports.out.CategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        if (page<0) throw new PageNumberLessThanZero();
        if (size<1) throw new PageSizeLessThanOne();
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }

}

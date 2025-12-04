package infrastructure.adapters.persistence;

import MicroservicioCasas.domain.model.CategoryModel;
import MicroservicioCasas.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import MicroservicioCasas.infrastructure.entities.CategoryEntity;
import MicroservicioCasas.infrastructure.mappers.CategoryEntityMapper;
import MicroservicioCasas.infrastructure.repositories.mysql.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryPersistenceAdapterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper entityMapper;

    @InjectMocks
    private CategoryPersistenceAdapter persistenceAdapter;


    @Test
    void save_category_success() {
        // Arrange
        CategoryModel model = new CategoryModel(null, "Tech", "Tech stuff");
        CategoryEntity entity = new CategoryEntity(null, "Tech", "Tech stuff");
        when(entityMapper.modelToEntity(model)).thenReturn(entity);

        // Act
        persistenceAdapter.save(model);

        // Assert
        verify(categoryRepository).save(entity);
    }


    @Test
    void get_by_name_category_success() {
        // Act
        persistenceAdapter.getCategoryByName(Mockito.anyString());
        // Assert
        verify(categoryRepository).findByName(Mockito.anyString());
    }

    @Test
    void getCategories_success() {
        // Arrange
        int page = 0;
        int size = 2;
        boolean orderAsc = true;


        CategoryEntity entity1 = new CategoryEntity(1L, "Tech", "Tech stuff");
        CategoryEntity entity2 = new CategoryEntity(2L, "Home", "Home stuff");
        List<CategoryEntity> entityList = Arrays.asList(entity1, entity2);

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<CategoryEntity> pagedResult = new PageImpl<>(entityList, pageable, entityList.size());

        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(pagedResult);

        CategoryModel model1 = new CategoryModel(1L, "Tech", "Tech stuff");
        CategoryModel model2 = new CategoryModel(2L, "Home", "Home stuff");
        when(entityMapper.entityListToModelList(entityList)).thenReturn(Arrays.asList(model1, model2));

        // Act
        List<CategoryModel> result = persistenceAdapter.getCategories(page, size, orderAsc);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Tech", result.get(0).getName());
        assertEquals("Home", result.get(1).getName());

        verify(categoryRepository).findAll(any(Pageable.class));
        verify(entityMapper).entityListToModelList(entityList);
    }
}
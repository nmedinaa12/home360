package MicroservicioCasas.configurations.beans;

import MicroservicioCasas.domain.ports.in.CategoryServicePort;
import MicroservicioCasas.domain.ports.in.LocationServicePort;
import MicroservicioCasas.domain.ports.out.CategoryPersistencePort;
import MicroservicioCasas.domain.ports.out.LocationPersistencePort;
import MicroservicioCasas.domain.usecases.CategoryUseCase;
import MicroservicioCasas.domain.usecases.LocationUseCase;
import MicroservicioCasas.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import MicroservicioCasas.infrastructure.adapters.persistence.LocationPersistenceAdapter;
import MicroservicioCasas.infrastructure.mappers.CategoryEntityMapper;
import MicroservicioCasas.infrastructure.mappers.LocationEntityMapper;
import MicroservicioCasas.infrastructure.repositories.mysql.CategoryRepository;
import MicroservicioCasas.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public CategoryPersistencePort categoryPersistencePort() {
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public LocationServicePort locationServicePort() {
        return new LocationUseCase(locationPersistencePort());
    }

    @Bean
    public LocationPersistencePort locationPersistencePort() {
        return new LocationPersistenceAdapter(locationRepository, locationEntityMapper);
    }
}

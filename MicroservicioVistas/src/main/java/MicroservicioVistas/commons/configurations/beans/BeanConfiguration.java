package MicroservicioVistas.commons.configurations.beans;

import MicroservicioVistas.domain.ports.in.TimeSlotServicePort;
import MicroservicioVistas.domain.ports.out.TimeSlotPersistencePort;
import MicroservicioVistas.domain.usecases.TimeSlotUseCase;
import MicroservicioVistas.infrastructure.adapters.TimeSlotPersistenceAdapter;
import MicroservicioVistas.infrastructure.mappers.TimeSlotEntityMapper;
import MicroservicioVistas.infrastructure.repositories.mysql.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotEntityMapper timeSlotEntityMapper;

    @Bean
    public TimeSlotServicePort timeSlotServicePort() {
        return new TimeSlotUseCase(timeSlotPersistencePort());
    }

    @Bean
    public TimeSlotPersistencePort timeSlotPersistencePort() {
        return new TimeSlotPersistenceAdapter(timeSlotRepository, timeSlotEntityMapper);
    }

    @Bean
    public TimeSlotUseCase timeSlotUseCase(TimeSlotPersistencePort timeSlotPersistencePort) {
        return new TimeSlotUseCase(timeSlotPersistencePort);
    }
}
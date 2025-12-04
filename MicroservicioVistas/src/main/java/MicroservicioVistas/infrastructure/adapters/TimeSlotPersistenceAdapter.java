package MicroservicioVistas.infrastructure.adapters;

import MicroservicioVistas.domain.model.TimeSlotModel;
import MicroservicioVistas.domain.ports.out.TimeSlotPersistencePort;
import MicroservicioVistas.infrastructure.mappers.TimeSlotEntityMapper;
import MicroservicioVistas.infrastructure.repositories.mysql.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeSlotPersistenceAdapter implements TimeSlotPersistencePort {
    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotEntityMapper timeSlotEntityMapper;

    @Override
    public void save(TimeSlotModel timeSlot){
        timeSlotRepository.save(timeSlotEntityMapper.toEntity(timeSlot));
    }
    @Override
    public List<TimeSlotModel> findOverlappingTimeSlots(Long sellerId, Long homeId, LocalDateTime startTime, LocalDateTime endTime) {
        return timeSlotRepository.findOverlappingTimeSlots(sellerId, homeId, startTime, endTime)
                .stream()
                .map(timeSlotEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}


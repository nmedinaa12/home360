package MicroservicioVistas.domain.ports.out;

import MicroservicioVistas.domain.model.TimeSlotModel;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotPersistencePort {
    void save(TimeSlotModel timeSlot);
    List<TimeSlotModel> findOverlappingTimeSlots(Long sellerId, Long homeId, LocalDateTime startTime, LocalDateTime endTime);
}

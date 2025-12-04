package MicroservicioVistas.domain.ports.in;

import MicroservicioVistas.domain.model.TimeSlotModel;

public interface TimeSlotServicePort {
    void save(TimeSlotModel timeSlot);
    void validateHome(Long requestedHomeId, Long actualHomeId);

}

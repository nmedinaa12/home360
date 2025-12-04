package MicroservicioVistas.application.services;

import MicroservicioVistas.application.dto.request.SaveTimeSlotRequest;
import MicroservicioVistas.application.dto.response.SaveTimeSlotResponse;

public interface TimeSlotService {
    SaveTimeSlotResponse save(SaveTimeSlotRequest request, Long userId);
}

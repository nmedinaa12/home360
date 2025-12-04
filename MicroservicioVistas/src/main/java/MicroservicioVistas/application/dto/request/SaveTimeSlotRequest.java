package MicroservicioVistas.application.dto.request;

import java.time.LocalDateTime;

public record SaveTimeSlotRequest(
        Long homeId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}

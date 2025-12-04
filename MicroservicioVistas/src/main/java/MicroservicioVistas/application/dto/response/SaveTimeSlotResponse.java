package MicroservicioVistas.application.dto.response;

import java.time.LocalDateTime;

public record SaveTimeSlotResponse(String message, LocalDateTime time) {
}

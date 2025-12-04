package MicroservicioCasas.infrastructure.exceptionshandler;


import MicroservicioCasas.domain.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NameMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleNameMaxSizeExceededException(NameMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.NAME_MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(DescriptionMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionMaxSizeExceededException(DescriptionMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.DESCRIPTION_MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.CATEGORY_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }

    @ExceptionHandler(PageNumberLessThanZero.class)
    public ResponseEntity<ExceptionResponse> handlePageNumberLessThanZero(PageNumberLessThanZero exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.PAGE_NUMBER_LESS_THAN_ZERO,
                LocalDateTime.now()));
    }

    @ExceptionHandler(PageSizeLessThanOne.class)
    public ResponseEntity<ExceptionResponse> handlePageSizeLessThanOne(PageSizeLessThanOne exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.PAGE_SIZE_LESS_THAN_ONE,
                LocalDateTime.now()));
    }


    //location
    @ExceptionHandler(DescriptionDepMaxSizeExceededException.class)
    public ResponseEntity<ExceptionResponse> handleDescriptionDepMaxSizeExceededException(DescriptionDepMaxSizeExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.DESCRIPTION_MAX_SIZE_MESSAGE,
                LocalDateTime.now()));
    }

    @ExceptionHandler(LocationAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleLocationAlreadyExistsException(LocationAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.LOCATION_EXISTS_EXCEPTION,
                LocalDateTime.now()));
    }

}

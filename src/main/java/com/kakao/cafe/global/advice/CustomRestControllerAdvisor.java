package com.kakao.cafe.global.advice;

import com.kakao.cafe.global.dto.CustomResponse;
import com.kakao.cafe.global.exception.CustomException;
import com.kakao.cafe.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CustomRestControllerAdvisor {

    /**
     * DTO Validation Exception Handler
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * 비즈니스 예외 처리 Handler
     * @param customException
     * @return
     */
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<CustomResponse> handleCustomException (CustomException customException) {
        return new ResponseEntity<>(CustomResponse.createErrorResponse(customException),
                HttpStatus.valueOf(customException.getErrorCode().getStatus()));
    }

    /**
     * RUNTIME Exception 예외 처리 Handler
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<CustomResponse> handleCustomException (Exception exception) {
        log.error("UNKNOWN exception occur : {}", exception.getMessage());
        CustomException customException = new CustomException(ErrorCode.SERVER_ERROR);
        return new ResponseEntity<>(CustomResponse.createErrorResponse(customException),
                HttpStatus.valueOf(customException.getErrorCode().getStatus()));
    }

}

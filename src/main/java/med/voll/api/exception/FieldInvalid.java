package med.voll.api.exception;

import org.springframework.validation.FieldError;

public record FieldInvalid(String field, String message) {
    public FieldInvalid(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}

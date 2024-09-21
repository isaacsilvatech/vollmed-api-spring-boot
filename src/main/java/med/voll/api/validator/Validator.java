package med.voll.api.validator;

import med.voll.api.exception.ValidationException;

public interface Validator<T> {

    void validate(T t) throws ValidationException;
}

package com.vandson.marvel.compartilhado;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 24/07/2020
 **/
public class ValidationErrorsOutput {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorOutput> fieldErrors = new ArrayList<>();

    public void addError(String message){
        getGlobalErrorMessages().add(message);
    }

    public void addFieldError(String field, String message){
        FieldErrorOutput fieldError = new FieldErrorOutput(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorOutput> getFieldErrors() {
        return fieldErrors;
    }
}

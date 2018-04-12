package com.basgeekball.awesomevalidation.validators;

import java.util.regex.Matcher;

import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.utility.ValidationCallback;

public class BasicValidator extends Validator {

    @Override
    public boolean trigger() {
        return checkFields(new ValidationCallback() {
            public void execute(ValidationHolder validationHolder, Matcher matcher) {
                validationHolder.getEditText().setError(setErrorTextColor(validationHolder.getErrMsg()));
            }
        });
    }

    @Override
    public void halt() {
        for (ValidationHolder validationHolder : mValidationHolderList) {
            validationHolder.getEditText().setError(null);
        }
    }

}
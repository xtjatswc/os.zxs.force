package com.basgeekball.awesomevalidation.validators;

import java.util.ArrayList;
import java.util.regex.Matcher;

import android.graphics.Color;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.helper.RangeHelper;
import com.basgeekball.awesomevalidation.helper.SpanHelper;
import com.basgeekball.awesomevalidation.utility.ValidationCallback;

public class ColorationValidator extends Validator {

    private int mColor = Color.RED;

    public void setColor(int color) {
        mColor = color;
    }

    public boolean trigger() {
        halt();
        return checkFields(new ValidationCallback() {
            public void execute(ValidationHolder validationHolder, Matcher matcher) {
                ArrayList<int[]> listOfMatching = new ArrayList<int[]>();
                while (matcher.find()) {
                    listOfMatching.add(new int[]{matcher.start(), matcher.end() - 1});
                }
                EditText editText = validationHolder.getEditText();
                ArrayList<int[]> listOfNotMatching = RangeHelper.inverse(listOfMatching, validationHolder.getText().length());
                SpanHelper.setColor(editText, mColor, listOfNotMatching);
                editText.setError(setErrorTextColor(validationHolder.getErrMsg()));
            }
        });
    }

    @Override
    public void halt() {
        for (ValidationHolder validationHolder : mValidationHolderList) {
            EditText editText = validationHolder.getEditText();
            editText.setError(null);
            SpanHelper.reset(editText);
        }
    }

}
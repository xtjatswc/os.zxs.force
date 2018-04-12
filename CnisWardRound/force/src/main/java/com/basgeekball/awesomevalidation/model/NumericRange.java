package com.basgeekball.awesomevalidation.model;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import com.google.common.collect.Range;

public class NumericRange {

    private Range mRange;

    public NumericRange(Range range) {
        mRange = range;
    }

    public static boolean isNumberFormat(String text) {
        return Pattern.compile("^([1-9]|(0(\\.|$)))").matcher(text).find();
    }

    public boolean isValid(String valueText) {
    	//如果没有填值，则不用做校验
    	if(valueText.equals("")){
    		return true;
    	}
    	
        if (!isNumberFormat(valueText)) {
            return false;
        }

        BigDecimal value;
        try {
            value = new BigDecimal(valueText);
        } catch (Exception e) {
            return false;
        }

        if (value.scale() == 0) {
            try {
                return mRange.contains(value.intValueExact());
            } catch (Exception e) {
            }
            try {
                return mRange.contains(value.longValueExact());
            } catch (Exception e) {
            }
        }
        try {
            return mRange.contains(value.floatValue());
        } catch (Exception e) {
        }
        try {
            return mRange.contains(value.doubleValue());
        } catch (Exception e) {
        }

        return false;
    }

}
package com.helper;

public class FormValidationUtils {
    static public boolean validataEmail(String email){
        return (email != null);
    }

    static public boolean validateEmpty(String field){
        return (field != null && !field.equals(""));
    }

}

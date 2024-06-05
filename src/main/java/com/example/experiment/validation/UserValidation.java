package com.example.experiment.validation;

import com.example.experiment.exceptions.UserValidatorException;
import com.example.experiment.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    private static final String EMAIL_REGEX = "^[\\w\\d]{1,20}@[\\w\\d]{1,20}\\.[a-zA-Z]{1,3}$";
    public static void validateUser(User user) throws UserValidatorException{
        List<String> errors = new ArrayList<>();
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.matches())
            errors.add("The email field is empty or it is not in the correct format!");
        if(user.getUsername().length() < 3 || user.getUsername().length() > 20)
            errors.add("The username field is empty or it is too long!");
        if(!errors.isEmpty())
            throw new UserValidatorException(errors);

    }
}

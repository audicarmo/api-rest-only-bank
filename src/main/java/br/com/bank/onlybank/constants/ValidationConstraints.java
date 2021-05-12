package br.com.bank.onlybank.constants;

public class ValidationConstraints {

    public static final String CPF_IS_REQUIRED = "CPF is required";
    public static final String DESCRIPTION_IS_REQUIRED = "Description is required";
    public static final String NUMBER_IS_REQUIRED = "Number is required";
    public static final String NAME_IS_REQUIRED = "Name is required";
    public static final String VALUE_REQUIRED = "Deleted is required";

    public static final String NUMBER_MAX_SIZE_IS = "Max number size is {max}";
    public static final String CPF_MAX_SIZE_IS = "Max CPF size is {max}";

    public static final String INVALID_CPF = "Invalid CPF : ${validatedValue}";
    public static final String INVALID_SPECIAL_CHARACTERS_CPF = "Invalid Non-Numeric CPF : ${validatedValue}";
    public static final String NAME_SIZE_MUST_BE_BETWEEN = "Name size must be between {min} and {max} characters long";
    public static final String DESCRIPTION_SIZE_MUST_BE_BETWEEN =
            "Description size must be between {min} and {max} characters long";

    public static final int CPF_SIZE = 11;

    public static final int DESCRIPTION_MIN_SIZE = 10;
    public static final int DESCRIPTION_MAX_SIZE = 150;
    public static final int NUMBER_MIN_SIZE = 2;
    public static final int NAME_MIN_SIZE = 3;
    public static final int NUMBER_MAX_SIZE = 5;
    public static final int NAME_MAX_SIZE = 40;
}

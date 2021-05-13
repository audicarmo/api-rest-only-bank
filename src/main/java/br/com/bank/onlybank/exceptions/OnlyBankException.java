package br.com.bank.onlybank.exceptions;

public class OnlyBankException extends  RuntimeException {

    private static final long serialVersionUID = 1L;

    public OnlyBankException(String message) {
        super(message);
    }

    public OnlyBankException(String message, Throwable cause) {
        super(message, cause);
    }
}

package br.com.bank.onlybank.enums;

public enum OperationType {

    DEPOSIT("Deposit"), REMOVAL("Removal"), TRANSFER("Transfer");

    private String description;

    OperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescricaption(String description) {
        this.description = description;
    }
}

package br.com.bank.onlybank.utils;

import java.io.Serializable;

public class AccountExtract implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long accountId;
    private Long extractId;

    public AccountExtract(Long accountId, Long extractId) {
        super();
        this.accountId = accountId;
        this.extractId = extractId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getExtractId() {
        return extractId;
    }

    public void setExtractId(Long extractId) {
        this.extractId = extractId;
    }
}

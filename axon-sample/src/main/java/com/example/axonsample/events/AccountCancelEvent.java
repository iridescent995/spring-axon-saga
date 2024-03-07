package com.example.axonsample.events;

public class AccountCancelEvent {
    private final String accountId;

    private final String name;

    public AccountCancelEvent(String accountId, String name) {
        this.accountId = accountId;
        this.name = name;
    }
    public String getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }
}

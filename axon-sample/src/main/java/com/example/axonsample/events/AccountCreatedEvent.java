package com.example.axonsample.events;

public class AccountCreatedEvent {
    private final String accountId;

    private final String name;

    public AccountCreatedEvent(String accountId, String name) {
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

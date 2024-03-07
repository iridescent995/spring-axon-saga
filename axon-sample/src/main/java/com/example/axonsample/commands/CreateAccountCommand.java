package com.example.axonsample.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private final String accountId;
    private final String name;
    private final String balance;


    public CreateAccountCommand(String accountId, String balance, String name) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
    public String getAccountId() {
        return accountId;
    }

    public String getBalance() {
        return balance;
    }


}

package com.example.axonsample.aggregate;

import com.example.axonsample.commands.CancelAccountCommand;
import com.example.axonsample.commands.CompleteAccountCommand;
import com.example.axonsample.commands.CreateAccountCommand;
import com.example.axonsample.events.AccountCancelEvent;
import com.example.axonsample.events.AccountCompletedEvent;
import com.example.axonsample.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Account {
    @AggregateIdentifier
    private String accountId;
    private Double balance;

    private String name;

    protected Account() {

    }

    @CommandHandler
    public Account(CreateAccountCommand command) {
        System.out.println("Account create command Handler");
        apply(new AccountCreatedEvent(command.getAccountId(), command.getName()));
    }

    @CommandHandler
    public void handle(CancelAccountCommand command) {
        System.out.println("Account cancel command Handler");
        apply(new AccountCancelEvent(command.getAccountId(), command.getName()));
    }

    @CommandHandler
    public void handle(CompleteAccountCommand command) {
        System.out.println("Account completed command Handler");
        apply(new AccountCompletedEvent(command.getAccountId(), command.getName()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.balance = 0.0;
        System.out.println("Account created :" + event.getAccountId());
    }

    @EventSourcingHandler
    protected void on(AccountCompletedEvent event) {
        this.accountId = event.getAccountId();
        this.balance = 0.0;
        System.out.println("Account completed :" + event.getAccountId());
    }

    @EventSourcingHandler
    protected void on(AccountCancelEvent event) {
        this.accountId = event.getAccountId();
        this.balance = 0.0;
        System.out.println("Account canceled :" + event.getAccountId());
    }

}

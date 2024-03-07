package com.example.axonsample.web;

import com.example.axonsample.commands.CreateAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class AxonController {

    private final CommandGateway commandGateway;

    @Autowired
    public AxonController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/accounts")
    public CompletableFuture<Object> createBankAccount(@RequestBody String name) {
        System.out.println("Init createBankAccount...");

        String accountId = UUID.randomUUID().toString();

        CreateAccountCommand createAccountCommand = new CreateAccountCommand(accountId, "0", name);

        return commandGateway.send(createAccountCommand);
    }



}

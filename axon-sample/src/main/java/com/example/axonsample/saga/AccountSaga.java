package com.example.axonsample.saga;

import com.example.axonsample.commands.CancelAccountCommand;
import com.example.axonsample.commands.CompleteAccountCommand;
import com.example.axonsample.commands.CreateAccountCommand;
import com.example.axonsample.events.AccountCancelEvent;
import com.example.axonsample.events.AccountCompletedEvent;
import com.example.axonsample.events.AccountCreatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Saga
public class AccountSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    public AccountSaga() {}

    @StartSaga
    @SagaEventHandler(associationProperty = "accountId")
    private void handle(AccountCreatedEvent event) {
        try {
            System.out.println("Account created saga");
            System.out.println(event.getAccountId());

            final String uri = "http://localhost:8003/camel/createShipping";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            System.out.println(result);

//            throw new Exception();
            CompleteAccountCommand completeAccountCommand = new CompleteAccountCommand(event.getAccountId(), "0", event.getName());
            commandGateway.sendAndWait(completeAccountCommand);
        }catch (Exception e){
            System.out.println("Exception " + e);
            CancelAccountCommand cancelAccountCommand = new CancelAccountCommand(event.getAccountId(), "0", event.getName());
            commandGateway.sendAndWait(cancelAccountCommand);
        }
    }

    @SagaEventHandler(associationProperty = "accountId")
    @EndSaga
    public void handle(AccountCompletedEvent event) {
        System.out.println("CompletedEvent in Saga for account Id : {}" +
                event.getAccountId());

    }

    @SagaEventHandler(associationProperty = "accountId")
    @EndSaga
    public void handle(AccountCancelEvent event) {
        System.out.println("cancel event in Saga for account Id : {}" +
                event.getAccountId());

    }
}

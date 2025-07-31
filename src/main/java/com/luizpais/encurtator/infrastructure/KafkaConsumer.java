package com.luizpais.encurtator.infrastructure;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaConsumer {

    @Incoming("redirectEvents-in")
    @Blocking
    public void consume(String message) {
        if (message != null) {
            System.out.println("Received message: " + message);
        }
    }
}
package com.luizpais.encurtator.infrastructure;

import io.quarkus.runtime.Startup;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
@Startup
public class QueueListener {

    @Inject
    LocalQueue localQueue;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    ;

    public QueueListener() {
        // Constructor
        executorService.submit(this::startListening);
    }

    public void startListening() {
        System.out.println("Starting to listen to the queue...");
        while (true) {
            String message = localQueue.dequeue();
            if (message != null) {
                System.out.println("Received message: " + message);
            }
            try {
                Thread.sleep(1000); // Avoid busy-waiting
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}


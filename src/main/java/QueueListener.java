import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.glassfish.jersey.model.Scoped;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
public class QueueListener {

    @Inject
    LocalQueue localQueue;

    private final ExecutorService executorService= Executors.newSingleThreadExecutor();;

    public QueueListener() {
        // Constructor
        startListening();
    }
    public void startListening() {
        System.out.println("Starting to listen to the queue...");
        executorService.submit(() -> {
            while (true) {
                String message = localQueue.dequeue();
                if (message != null) {
                    System.out.println("Received message: " + message);
                }
                try {
                    Thread.sleep(100); // Avoid busy-waiting
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }

    public void stopListening() {
        executorService.shutdownNow();
    }
}
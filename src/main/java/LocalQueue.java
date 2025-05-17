import jakarta.enterprise.context.ApplicationScoped;

import java.util.LinkedList;
import java.util.Queue;

@ApplicationScoped
public class LocalQueue {

    private final Queue<String> queue;

    public LocalQueue() {
        this.queue = new LinkedList<>();
    }

    // Add a message to the queue
    public void enqueue(String message) {
        queue.offer(message);
        System.out.println("Message added to the queue: " + message);
    }

    // Retrieve and remove a message from the queue
    public String dequeue() {
        String message = queue.poll();
        if (message == null) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Message retrieved from the queue: " + message);
        }
        return message;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return queue.size();
    }

}



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QueueSender {

    @Inject
    LocalQueue queue;

    public void sendMessage(Object messageContent) {

        // Create a string from the message content
        String stringContent = JsonUtil.toJson(messageContent);
        queue.enqueue(stringContent);
        System.out.println("Message sent! " + stringContent);
    }
}

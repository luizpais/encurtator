package com.luizpais.encurtator.infrastructure;


import com.luizpais.utils.JsonUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class QueueSender {

    @Inject
    LocalQueue queue;

    @Inject
    JsonUtil jsonUtil;

    public void sendMessage(Object messageContent) {

        // Create a string from the message content
        String stringContent = jsonUtil.toJson(messageContent);
        queue.enqueue(stringContent);
        System.out.println("Message sent! " + stringContent);
    }
}

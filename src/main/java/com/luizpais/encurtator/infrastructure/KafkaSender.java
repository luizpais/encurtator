package com.luizpais.encurtator.infrastructure;


import com.luizpais.utils.JsonUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaSender {

    @Inject
    @Channel("redirectEvents-out")
    Emitter<String> redirectEvents;

    @Inject
    JsonUtil jsonUtil;

    public void sendMessage(Object messageContent) {

        // Create a string from the message content
        String stringContent = jsonUtil.toJson(messageContent);
        redirectEvents.send(stringContent);
        System.out.println("Message sent! " + stringContent);
    }
}

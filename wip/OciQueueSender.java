

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.auth.InstancePrincipalsAuthenticationDetailsProvider;
import com.oracle.bmc.queue.model.PutMessagesDetails;
import com.oracle.bmc.queue.model.PutMessagesDetailsEntry;
import com.oracle.bmc.queue.requests.PutMessagesRequest;
import com.oracle.bmc.queue.responses.PutMessagesResponse;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import com.oracle.bmc.queue.QueueClient;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class OciQueueSender {

    private QueueClient queueClient;

    private static final String QUEUE_OCID = "ocid1.queue.oc1.sa-saopaulo-1.amaaaaaacwavyayap3o5bfztxjnsmv4yyicupo4wrck2r2jex5hltv5ncbva"; // Example: ocid1.queue.oc1..aaaa...

    @PostConstruct
    void init() throws IOException {
        // Use Instance Principal (works inside OCI VM, Function, OKE)
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider("~/.oci/config", "EncurtatorSystemUser");

        queueClient = QueueClient.builder()
                .build(provider);
    }

    public void sendMessage(Object messageContent) {

        // Create a string from the message content
        String stringContent = JsonUtil.toJson(messageContent);
        PutMessagesDetailsEntry message = PutMessagesDetailsEntry.builder()
                .content(stringContent)
                .build();

        PutMessagesDetails messagesDetails = PutMessagesDetails.builder()
                .messages(List.of( message))
                .build();

        PutMessagesRequest request = PutMessagesRequest.builder()
                .queueId(QUEUE_OCID)
                .putMessagesDetails(messagesDetails)
                .build();

        PutMessagesResponse response = queueClient.putMessages(request);
        System.out.println("Message sent with status: " + response.get__httpStatusCode__());
    }
}

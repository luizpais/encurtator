//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.eclipse.microprofile.reactive.messaging.Emitter;
//
//// AnalyticsEventPublisher.java
//@ApplicationScoped
//public class AnalyticsEventPublisher {
//    @Inject
//    @Channel("click-events")
//    Emitter<ClickEventDTO> emitter;
//
//    public void publish(ClickEventDTO event) {
//        emitter.send(event);
//    }
//}

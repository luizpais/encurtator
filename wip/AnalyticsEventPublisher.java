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
//    Emitter<com.luizpais.encurtator.model.ClickEventDTO> emitter;
//
//    public void publish(com.luizpais.encurtator.model.ClickEventDTO event) {
//        emitter.send(event);
//    }
//}

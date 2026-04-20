package beny.logisticsservice;

import beny.logisticsservice.event.DeliveryUpdateMessage;
import beny.logisticsservice.services.LocationBroadcastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test — LG-002
 * Given buyer subscribed to /topic/delivery/{orderId}
 * When  broadcast service publishes a location update
 * Then  buyer receives the payload within 5 seconds
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketLocationTest {

    @LocalServerPort
    int port;
    @Autowired
    LocationBroadcastService broadcastService;

    private WebSocketStompClient stompClient;

    @BeforeEach
    void setUp() {
        stompClient = new WebSocketStompClient(
                new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient())))
        );
        stompClient.setMessageConverter(new JacksonJsonMessageConverter());
    }

    @Test
    void buyerReceivesLocationUpdate() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<DeliveryUpdateMessage> received = new AtomicReference<>();

        StompSession session = stompClient
                .connectAsync("ws://localhost:" + port + "/ws",
                        new WebSocketHttpHeaders(), new StompSessionHandlerAdapter() {
                        })
                .get(3, TimeUnit.SECONDS);

        session.subscribe("/topic/delivery/order-123", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders h) {
                return DeliveryUpdateMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders h, Object payload) {
                received.set((DeliveryUpdateMessage) payload);
                latch.countDown();
            }
        });

        broadcastService.broadcastLocationUpdate("order-123", DeliveryUpdateMessage.builder()
                .shipmentId("order-123")
                .lat(6.5500)
                .lng(3.3700)
                .estimatedArrivalMins(12)
                .status("IN_TRANSIT")
                .build());

        assertThat(latch.await(5, TimeUnit.SECONDS)).isTrue();
        assertThat(received.get().getLat()).isEqualTo(6.5500);
        assertThat(received.get().getLng()).isEqualTo(3.3700);
        assertThat(received.get().getStatus()).isEqualTo("IN_TRANSIT");

        session.disconnect();
    }
}
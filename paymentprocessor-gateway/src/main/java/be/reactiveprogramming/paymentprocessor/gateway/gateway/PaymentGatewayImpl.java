package be.reactiveprogramming.paymentprocessor.gateway.gateway;

import be.reactiveprogramming.paymentprocessor.common.event.PaymentEvent;
import be.reactiveprogramming.paymentprocessor.gateway.command.CreatePaymentCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private static final Logger log = LoggerFactory.getLogger(PaymentGatewayImpl.class.getName());

    private KafkaSender kafkaProducer;

    private ObjectMapper objectMapper = new ObjectMapper();

    private String gatewayName = "1";

    /**
     * Here we do some basic setup of Project Reactor Kafka to be able to send messages
     */
    public PaymentGatewayImpl() {
        final Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        final SenderOptions<Integer, String> producerOptions = SenderOptions.create(producerProps);

        kafkaProducer = KafkaSender.create(producerOptions);
    }


    @Override
    public Mono<Void> doPayment(final CreatePaymentCommand createPayment) {
        final PaymentEvent payment = new PaymentEvent(createPayment.getId(), createPayment.getCreditCardNumber(), createPayment.getAmount(), gatewayName);

        String payload = toBinary(payment);

        SenderRecord<Integer, String, Integer> message = SenderRecord.create(new ProducerRecord<>("unconfirmed-transactions", payload), 1);
        return kafkaProducer.send(Mono.just(message)).next();
    }

    private String toBinary(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

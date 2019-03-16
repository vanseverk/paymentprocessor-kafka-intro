package be.reactiveprogramming.paymentprocessor.gateway.gateway;

import be.reactiveprogramming.paymentprocessor.gateway.command.CreatePaymentCommand;
import reactor.core.publisher.Mono;

public interface PaymentGateway {

    Mono<Void> doPayment(CreatePaymentCommand createPayment);

}

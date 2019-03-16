package be.reactiveprogramming.paymentprocessor.paymentvalidator.validator;

import be.reactiveprogramming.paymentprocessor.common.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidatorImpl implements PaymentValidator {

    private Logger LOGGER = LoggerFactory.getLogger(PaymentValidatorImpl.class);

    @Override
    public void calculateResult(PaymentEvent paymentEvent) {
        LOGGER.info("Processing " + paymentEvent.getCreditCardNumber() + " " + paymentEvent.getAmount());
    }
}

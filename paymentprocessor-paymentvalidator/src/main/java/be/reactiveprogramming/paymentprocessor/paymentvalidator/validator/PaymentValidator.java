package be.reactiveprogramming.paymentprocessor.paymentvalidator.validator;

import be.reactiveprogramming.paymentprocessor.common.event.PaymentEvent;

public interface PaymentValidator {

    void calculateResult(PaymentEvent paymentEvent);

}

package be.reactiveprogramming.paymentprocessor.paymentvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class PaymentValidatorApp {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(PaymentValidatorApp.class, args);
  }

}

package be.reactiveprogramming.paymentprocessor.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class PaymentGatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(PaymentGatewayApp.class, args);
  }

}

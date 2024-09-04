package com.ecom.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "PAYMENT-SERVICE",
        url = "${myapp.payment-url}"
)
public interface PaymentClient {
@PostMapping
    public Integer makePayment(@RequestBody PaymentRequest paymentRequest);
}

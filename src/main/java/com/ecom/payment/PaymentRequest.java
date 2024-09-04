package com.ecom.payment;

import com.ecom.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;

import java.math.BigDecimal;


public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {

}

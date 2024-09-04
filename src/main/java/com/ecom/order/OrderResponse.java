package com.ecom.order;

import com.ecom.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(Integer id,
                            String reference,
                            BigDecimal amount,
                            PaymentMethod paymentMethod,
                            String customerId) {
}

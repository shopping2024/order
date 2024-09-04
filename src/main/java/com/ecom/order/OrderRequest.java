package com.ecom.order;

import com.ecom.orderline.OrderLine;
import com.ecom.payment.PaymentMethod;
import com.ecom.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(Integer id,
                           String reference,
                           @Positive(message = "amount should be positive")
                           BigDecimal totalAmount,
                           @NotNull(message = "payment method should be precised")
                           PaymentMethod paymentMethod,
                           @NotNull(message = "customer should be present")
                           @NotEmpty(message = "customer should be present")
                                   @NotBlank(message = "customer should be present")
                           String customerId,
                           @NotEmpty(message = "you should be purchase at least one product")
                           List<PurchaseRequest> product
                           ) {
}

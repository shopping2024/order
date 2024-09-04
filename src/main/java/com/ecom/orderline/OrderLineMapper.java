package com.ecom.orderline;

import com.ecom.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
      return   OrderLine.builder().id(request.id())

                .order(
                        Order.builder().id(request.orderId())
                                . build()
                )
              .productId(request.productId())
                        .quantity(request.quantity())
                                .
                build();
    }
    public OrderLineResponse fromOrderLine(OrderLine orderLine){
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }

}

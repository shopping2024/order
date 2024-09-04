package com.ecom.order;

import com.ecom.customer.CustomerClient;
import com.ecom.customer.CustomerResponse;
import com.ecom.exception.BusinessException;
import com.ecom.kafka.OrderConformation;
import com.ecom.kafka.OrderProducer;
import com.ecom.orderline.OrderLineRequest;
import com.ecom.orderline.OrderLineService;
import com.ecom.payment.PaymentClient;
import com.ecom.payment.PaymentRequest;
import com.ecom.product.ProductClient;
import com.ecom.product.PurchaseRequest;
import com.ecom.product.PurchaseResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private  CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private PaymentClient  paymentClient;
    public Integer createOrder(OrderRequest request){
       CustomerResponse response= customerClient.findCustomer(request.customerId())
                .orElseThrow(()->new BusinessException("can not create order customer is not available with provided id"));
        System.out.println(request.product());
       List<PurchaseResponse> purchaseResponses =productClient.purchaseProducts(request.product());
Order order=orderRepository.save(orderMapper.toOrder(request));
for(PurchaseRequest purchaseRequest:request.product()){
    orderLineService.saveOrderLine(
            new OrderLineRequest(null,
                    request.id(), purchaseRequest.id(), purchaseRequest.quantity())
    );
}
        var paymentRequest = new PaymentRequest(
                request.totalAmount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                response
        );
paymentClient.makePayment(paymentRequest);
orderProducer.sendOrderConformation(new OrderConformation(
        request.reference(),request.totalAmount(),request.paymentMethod(),response,purchaseResponses));
return order.getId();
    }

public List<OrderResponse> findAll(){
       return orderRepository.findAll().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
}
    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }


}

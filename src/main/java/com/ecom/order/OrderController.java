package com.ecom.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderService orderService;
    @PostMapping
    @CircuitBreaker(name = "orderService",fallbackMethod ="fallBackMethod" )
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }
    public ResponseEntity<Integer> fallBackMethod(Exception e) {
        // Log the error for debugging

        logger.error ("Fallback called due to service is down try again latter: ", e.getMessage());

        // Return a user-friendly fallback response
       return new ResponseEntity<>(null,HttpStatus.OK) ;
    }
}

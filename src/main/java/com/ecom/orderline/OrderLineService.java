package com.ecom.orderline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private OrderLineMapper orderLineMapper;
    public Integer saveOrderLine(OrderLineRequest request){
        OrderLine orderLine=orderLineMapper.toOrderLine(request);
return orderLineRepository.save(orderLine).getId();
    }
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::fromOrderLine)
                .collect(Collectors.toList());
    }

}

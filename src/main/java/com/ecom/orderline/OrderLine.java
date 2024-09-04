package com.ecom.orderline;

import com.ecom.order.Order;
import jakarta.persistence.*;
import jdk.jshell.spi.ExecutionControl;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    @JoinColumn(name="order_Id")
    @ManyToOne
    private Order order;
    private double quantity;
}

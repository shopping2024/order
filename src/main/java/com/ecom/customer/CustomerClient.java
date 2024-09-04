package com.ecom.customer;

import com.ecom.exception.BusinessException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "CUSTOMER-SERVICE",
        url = "${myapp.customer-url}"
)
public interface CustomerClient {
    @GetMapping("/{id}")
    public Optional<CustomerResponse> findCustomer(@PathVariable String id);
}

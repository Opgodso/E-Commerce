package com.example.notebook.commercebackend.Dto;

import com.example.notebook.commercebackend.entity.Customer;
import com.example.notebook.commercebackend.entity.Address;
import com.example.notebook.commercebackend.entity.Order;
import com.example.notebook.commercebackend.entity.OrderItem;
import lombok.Data;
import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}

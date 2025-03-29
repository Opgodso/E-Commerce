package com.example.notebook.commercebackend.Service;

import com.example.notebook.commercebackend.Dto.Purchase;
import com.example.notebook.commercebackend.Dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.notebook.commercebackend.entity.Order;
import com.example.notebook.commercebackend.Dto.PaymentInfo;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
    Page<Order> findOrdersByCustomerEmail(String email, Pageable pageable);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}

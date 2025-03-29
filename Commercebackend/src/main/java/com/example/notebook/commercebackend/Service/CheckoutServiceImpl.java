package com.example.notebook.commercebackend.Service;

import com.example.notebook.commercebackend.Dao.CustomerRepository;
import com.example.notebook.commercebackend.Dao.OrderRepository;
import com.example.notebook.commercebackend.Dto.PaymentInfo;
import com.example.notebook.commercebackend.Dto.Purchase;
import com.example.notebook.commercebackend.Dto.PurchaseResponse;
import com.example.notebook.commercebackend.entity.Customer;
import com.example.notebook.commercebackend.entity.Order;
import com.example.notebook.commercebackend.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               OrderRepository orderRepository,
                               @Value("${stripe.key.secret}") String secretKey) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();

        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(order::add);

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();
        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    public Page<Order> findCustomerByEmail(String email, Pageable pageable) {
        return orderRepository.findByCustomerEmail(email, pageable);
    }

    private String generateOrderTrackingNumber(){
        return UUID.randomUUID().toString();
    }

    @Override
    public Page<Order> findOrdersByCustomerEmail(String email, Pageable pageable) {
        return orderRepository.findByCustomerEmail(email, pageable);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount",paymentInfo.getAmount());
        params.put("currency",paymentInfo.getCurrency());
        params.put("payment_method_types",paymentMethodTypes);
        return PaymentIntent.create(params);
    }
}

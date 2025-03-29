package com.example.notebook.commercebackend.Controller;

import com.stripe.exception.StripeException;
import com.example.notebook.commercebackend.Dto.PaymentInfo;
import com.example.notebook.commercebackend.Dto.Purchase;
import com.example.notebook.commercebackend.Dto.PurchaseResponse;
import com.example.notebook.commercebackend.Service.CheckoutService;
import com.stripe.model.PaymentIntent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.notebook.commercebackend.entity.Order;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {this.checkoutService = checkoutService;}

    @GetMapping
    public Page<Order> getOrdersByCustomerEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return checkoutService.findOrdersByCustomerEmail(email, PageRequest.of(page, size));
    }


    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;

    }

    @PostMapping("/payment")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("client_secret", paymentIntent.getClientSecret());

        return ResponseEntity.ok(responseData);
    }

}

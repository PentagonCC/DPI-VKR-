package com.example.vkr.controller;

import com.example.vkr.model.*;
import com.example.vkr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @GetMapping("/registration-order")
    public String getRegistrationOrderPage(@RequestParam("totalPrice") double totalPrice,
                                           @RequestParam("bonusBalance") int bonusBalance,
                                           @AuthenticationPrincipal User user, Model model){
        model.addAttribute("bonusBalance", bonusBalance);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("user", user);
        return "registration-order";
    }

    @GetMapping("/orders")
    public String getOrdersPage(@AuthenticationPrincipal User user, Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("user", user);
        return "orders";
    }

    @PostMapping("/registration-order/accept")
    public String createOrder(@RequestParam("totalPrice") double totalPrice,
                              @RequestParam("bonusBalance") int bonusBalance,
                              @RequestParam("paymentMethod") String paymentMethod,
                              @AuthenticationPrincipal User user){
        int cashBack = 0;
        List<Cart> cartList = cartService.getCartByUserId(user.getId());
        Map<Product, Integer> cartItems = new HashMap<>();
        for(Cart cart : cartList) {
            Long productId = cart.getProduct().getId();
            cartItems.put(productService.getProductById(productId), cart.getQuantity());
        }
        for(Product product : cartItems.keySet()){
            if(cashBack < product.getCategory().getCashBack()){
                cashBack = product.getCategory().getCashBack() / cartItems.size();
            }
        }
        int totalBonusBalance = (int) (bonusBalance + (totalPrice * cashBack / 100));
        user.setBonuses(bonusBalance);
        user.setBonuses(totalBonusBalance);
        userService.updateBonusesUser(user.getId(), totalBonusBalance);
        LocalDateTime createdAt = LocalDateTime.now();
        String status = OrderStatus.NEW.getTitle();
        Long orderId = orderService.addNewOrder(user, totalPrice, status, createdAt);
        paymentService.insertPayment(orderId, PaymentStatus.WAITING.getTitle(), paymentMethod);
        for(Map.Entry<Product, Integer> orderItem : cartItems.entrySet()){
            int quantity = orderItem.getValue();
            Product item = orderItem.getKey();
            orderItemService.addOrderItems(orderId, item.getId(), quantity, item.getPrice());
            item.setStockQuantity(item.getStockQuantity() - quantity);
            productService.updateStockQuantity(item.getStockQuantity(), item.getId());
        }
        cartService.clearCart(user.getId());
        return "redirect:/";
    }
}

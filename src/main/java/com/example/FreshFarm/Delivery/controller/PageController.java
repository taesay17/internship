package com.example.FreshFarm.Delivery.controller;

import com.amazonaws.services.dynamodbv2.document.Page;
import com.example.FreshFarm.Delivery.model.domain.Product;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthLoginRequest;
import com.example.FreshFarm.Delivery.model.dto.auth.AuthRegisterRequest;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketProductsPrice;
import com.example.FreshFarm.Delivery.model.dto.basket.BasketResponse;
import com.example.FreshFarm.Delivery.model.dto.product.ProductResponse;
import com.example.FreshFarm.Delivery.service.BasketService;
import com.example.FreshFarm.Delivery.service.CommentService;
import com.example.FreshFarm.Delivery.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/pages")
public class  PageController {
    private final ProductService productService;
    private final CommentService commentService;
    private final BasketService basketService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("authLoginRequest", new AuthLoginRequest());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("authRegisterRequest", new AuthRegisterRequest());
        return "register";
    }

    @GetMapping("/blog")
    public String blog(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        List<ProductResponse> products = productService.all(page, size);

        Map<Long, Integer> commentCounts = products.stream()
                .collect(Collectors.toMap(ProductResponse::getId, p -> commentService.getAmountOfComments(p.getId())));

        model.addAttribute("title", "Fresh Farm Delivery");
        model.addAttribute("products", products);
        model.addAttribute("commentCounts", commentCounts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productService.totalPages(page, size));
        model.addAttribute("size", size);

        return "blog";
    }


    @GetMapping("/blog-details")
    public String blogDetails(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "blog-details";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "checkout";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "contact";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("title", "Fresh Farm Delivery");
        return "main";
    }

    @GetMapping("/shop-details/{id}")
    public String shopDetails(
            @PathVariable Long id,
            Model model
    ) {
        ProductResponse response = productService.getDetail(id);
        List<ProductResponse> relatedProducts = productService.getRelatedProducts(response.getName(), response.getPrice(), response.getId(), 0, 10);

        model.addAttribute("product", response);
        model.addAttribute("relatedProducts", relatedProducts);
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shop-details";
    }

    @GetMapping("/shop-grid")
    public String shopGrid(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortType,
            Model model
    ) {
        model.addAttribute("title", "Fresh Farm Delivery");

        List<ProductResponse> productResponses;
        if (sortBy != null) {
            productResponses = productService.sortBy(sortBy, sortType, page, size);
        } else {
            productResponses = productService.all(page, size);
        }
        model.addAttribute("products", productResponses);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productService.totalPages(page, size));
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortType", sortType);
        model.addAttribute("discountedProducts", productService.getDiscountedProducts(0, 6));
        model.addAttribute("latestProducts", productService.sortBy("createdAt", "asc", 0, 9));

        return "shop-grid";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart(
            @CookieValue(name = "access_token") String token,
            Model model
    ) {

        List<BasketResponse> basketItems = basketService.getCustomerBasket("Bearer " + token);
        BasketProductsPrice totalPrice = basketService.getPrice("Bearer " + token);

        model.addAttribute("basketItems", basketItems);
        model.addAttribute("totalPrice", totalPrice.getTotalPrice());
        model.addAttribute("title", "Fresh Farm Delivery");
        return "shoping-cart";
    }
}

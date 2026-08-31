package com.company.controller;

import com.company.api.order.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.company.api.user.UserService;

@RestController
public class HelloController
{
    private final UserService userService;
    private final OrderService orderService;

    public HelloController(UserService userService, OrderService orderService)
    {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/user/greet")
    public String hello(@RequestParam(defaultValue = "abc") String name)
    {
        return userService.greet(name);
    }

    @GetMapping("/user/module")
    public String helloMeta()
    {
        return "User service provided by " + userService.moduleName();
    }

    @GetMapping("/order/sum")
    public String sum(@RequestParam(defaultValue = "Codex") String orderId)
    {
        return orderService.summary(orderId);
    }


}

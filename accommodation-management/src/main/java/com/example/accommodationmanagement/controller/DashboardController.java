package com.example.accommodationmanagement.controller;

import com.example.accommodationmanagement.model.User;
import com.example.accommodationmanagement.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String pressUser(@ModelAttribute("user") User user) {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsersForm() {
        return "users";
    }
}

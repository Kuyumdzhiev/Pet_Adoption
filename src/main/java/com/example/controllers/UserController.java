package com.example.controllers;

import com.example.configuration.auth.AuthenticationResponse;
import com.example.persistence.binding.UserLoginBindingModel;
import com.example.persistence.binding.UserRegisterBindingModel;
import com.example.persistence.entities.UserEntity;
import com.example.service.AuthenticationService;
import com.example.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")  //@RequestMapping("/Authentication")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationServiceService;

    // Display the form to the user
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserRegisterBindingModel()); /*RegisterRequest in video*/
        return "register"; // this is the name of the view (e.g., a Thymeleaf template) to display
    }

    // Handle the form submission
    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegisterBindingModel request, Model model) /*UserRegisterBindingModel*/ {
        AuthenticationResponse response = authenticationServiceService.register(request);
        model.addAttribute("user", response);
        return "redirect:/"; // successful reg
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserLoginBindingModel());
        return "login";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/login")
    public String authenticate(@ModelAttribute("user") UserLoginBindingModel request, Model model, HttpServletResponse response) {
        AuthenticationResponse authResponse = authenticationServiceService.authenticate(request);
        Cookie jwtCookie = new Cookie("jwt", authResponse.getUserAccessToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true); // This makes the cookie only be sent over HTTPS. Use it if your site is served over HTTPS.
        jwtCookie.setMaxAge(24 * 60 * 60); // Set
        response.addCookie(jwtCookie);
        model.addAttribute("user", request);
        return "redirect:/";
    }


    @PostMapping("/refresh-token")
    @ResponseBody
    public AuthenticationResponse refreshToken(@RequestParam String refreshToken) throws IOException {
        return authenticationServiceService.refreshToken(refreshToken);
    }

}

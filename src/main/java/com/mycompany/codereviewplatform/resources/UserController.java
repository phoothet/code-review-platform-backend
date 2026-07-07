package com.mycompany.codereviewplatform.resources;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Front-end နဲ့ အလွယ်တကူ ချိတ်ဆက်နိုင်ရန် ကြိုတင်ခွင့်ပြုထားခြင်း
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // HTTP POST: http://localhost:8080/api/users/register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // HTTP GET: http://localhost:8080/api/users
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }
    
    // HTTP POST: http://localhost:8080/api/users/login
    @PostMapping("/login")
    public org.springframework.http.ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            User authenticatedUser = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            return org.springframework.http.ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            // အမှားတစ်ခုခုရှိပါက Error Message ကို တုံ့ပြန်ပေးခြင်း
            return org.springframework.http.ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
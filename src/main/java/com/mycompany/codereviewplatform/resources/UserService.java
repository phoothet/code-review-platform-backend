package com.mycompany.codereviewplatform.resources;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User registerUser(User user) {
        
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username သုံးပြီးသားဖြစ်နေပါသည်!");
        }
        return userRepository.save(user);
    }
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // အကောင့်ဝင်ခြင်းကို စစ်ဆေးပေးသည့် လုပ်ငန်းစဉ်
    public User loginUser(String username, String password) {
        // ၁။ အရင်ဆုံး Username ရှိမရှိ ရှာဖွေခြင်း
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username မရှိပါ၊ အကောင့် အရင်ဆောက်ပါ!"));

        // ၂။ ရိုက်ထည့်လိုက်သော Password နှင့် Database ထဲက Password တိုက်ဆိုင်စစ်ဆေးခြင်း
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password မှားယွင်းနေပါသည်!");
        }

        // ၃။ အကုန်မှန်ကန်ပါက အကောင့်ဝင်ခွင့်ပြုပြီး User ဒေတာကို ပြန်ပေးခြင်း
        return user;
    }
}

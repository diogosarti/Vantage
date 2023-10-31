package com.osguri.vantage.resources;

import com.osguri.vantage.entities.User;
import com.osguri.vantage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AuthResource {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/access-denied")
    public String redirect(){
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/login")
    public String login(Model model, Authentication authentication) {
        model.addAttribute("user", new User());
        if (authentication != null) {
            return "redirect:/dashboard";
        }
        return "auth/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model, Authentication authentication){
        model.addAttribute("user", new User());
        if (authentication != null) {
            return "redirect:/dashboard";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/login?success";
    }
}

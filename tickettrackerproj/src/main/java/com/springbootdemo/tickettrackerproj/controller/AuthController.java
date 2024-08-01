package com.springbootdemo.tickettrackerproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbootdemo.tickettrackerproj.dto.UserDto;
import com.springbootdemo.tickettrackerproj.entity.Ticket;
import com.springbootdemo.tickettrackerproj.entity.User;
import com.springbootdemo.tickettrackerproj.service.TicketServiceImpl;
import com.springbootdemo.tickettrackerproj.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class AuthController {

	private UserService userService;
	
//	public AuthController() {}
	
	public AuthController(UserService userService) {
		
		this.userService = userService;
	}
	
	
	
	@GetMapping("/index")
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        //System.out.println("Created User : "+user.getFirstName()+" "+user.getLastName());
        return "redirect:/user/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

	
	
}

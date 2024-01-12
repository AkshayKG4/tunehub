package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class usersController {
	
	@Autowired
	UsersService service;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user)
	{
		boolean userStatus = service.emailExists(user.getEmail());
		if(userStatus == false)
		{
		service.addUser(user);
		System.out.println("user added");
		}else
		{
			System.out.println("user already exists");
		}
		return "home";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,@RequestParam("password")String password, HttpSession session)
	{
		if(service.validateUser(email, password)==true)
		{
			String role = service.getRole(email);
			
			session.setAttribute("email", email);
			if(role.equals("admin"))
			{
				return "admin";
			}
			else
			{
				return "customer";
			}
			
		}
		else
		{
			return "login";
		}
	}
}

	/*
	@GetMapping("/pay")
	public String pay(@RequestParam String email)
	{
	boolean paymentStatus = false;//payment api
	
	if(paymentStatus == true)
	{
		Users user = service.getUser(email);
		user.setPremium(true);
		service.updateUser(user);
	}
	 return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}		
}
*/
	
/*
public String addUsers(@RequestParam("username") String username,
                      @RequestParam("email") String email,
                      @RequestParam("password") String password,
                      @RequestParam("gender") String gender,
                      @RequestParam("role") String role,
                      @RequestParam("address") String address ) 
*/

/*
public String addUsers(@ModelAttribute Users user)
{
	System.out.println(user.getUsername()+""+user.getEmail()+""+user.getPassword()+""+user.getGender()+""+user.getRole()+""+""+user.getAddress());
	return "home";
}
*/


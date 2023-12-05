package com.spring.first.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.first.springproject.Entity.User;
import com.spring.first.springproject.Repository.UserRepository;

@Controller
@RequestMapping(path="/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (
    @RequestParam String name,
    @RequestParam String email,
    @RequestParam String mot_de_passe,
    @RequestParam String role
  ) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    n.setMot_de_passe(mot_de_passe);
    n.setRole(role);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
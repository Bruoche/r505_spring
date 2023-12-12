package com.spring.first.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.first.springproject.Entity.Article;
import com.spring.first.springproject.Entity.User;
import com.spring.first.springproject.Repository.ArticleRepository;
import com.spring.first.springproject.Repository.UserRepository;

@Controller
@RequestMapping(path="/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ArticleRepository articleRepository;

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

  @Transactional
  @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).get();
        Iterable<Article> articles = articleRepository.findByAuteur(user);
        for (Article article : articles) {
          articleRepository.delete(article);
        }
        userRepository.delete(user);
        return "Deleted";
    }
}
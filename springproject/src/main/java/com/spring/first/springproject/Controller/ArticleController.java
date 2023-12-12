package com.spring.first.springproject.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.first.springproject.Entity.Article;
import com.spring.first.springproject.Entity.User;
import com.spring.first.springproject.Repository.ArticleRepository;
import com.spring.first.springproject.Repository.UserRepository;

@Controller
@RequestMapping(path="/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewArticle (
        @RequestParam Integer auteurId,
        @RequestParam String contenu
    ) {
        Article n = new Article();
        User auteur = userRepository.findById(auteurId).get();
        n.setAuteur(auteur);
        n.setContenu(contenu);
        n.setDate_publication(new Date());
        articleRepository.save(n);
        return "Saved";
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateArticle (
        @PathVariable Integer id,
        @RequestParam Integer auteurId,
        @RequestParam String contenu
    ) {
        Article n = articleRepository.findById(id).get();
        User auteur = userRepository.findById(auteurId).get();
        n.setAuteur(auteur);
        n.setContenu(contenu);
        articleRepository.save(n);
        return "Modified";
    }

    @PutMapping(path="like/{id}/{userId}")
    public @ResponseBody ResponseEntity<String> likeArticle (
        @PathVariable Integer id,
        @PathVariable Integer userId
    ) {
        Article article = articleRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        if (article.getLikes().contains(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The article have already been liked by this user.");
        }
        if (article.getDislikes().contains(user)) {
            article.getDislikes().remove(user);
        }
        article.getLikes().add(user);
        articleRepository.save(article);
        return ResponseEntity.ok().body("Liked");
    }

    @PutMapping(path="dislike/{id}/{userId}")
    public @ResponseBody ResponseEntity<String> dislikeArticle (
        @PathVariable Integer id,
        @PathVariable Integer userId
    ) {
        Article article = articleRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        if (article.getDislikes().contains(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The article have already been disliked by this user.");
        }
        if (article.getLikes().contains(user)) {
            article.getLikes().remove(user);
        }
        article.getDislikes().add(user);
        articleRepository.save(article);
        return ResponseEntity.ok().body("Disliked");
    }


    @PutMapping(path="unlike/{id}/{userId}")
    public @ResponseBody ResponseEntity<String> unlikeArticle (
        @PathVariable Integer id,
        @PathVariable Integer userId
    ) {
        Article article = articleRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        if (article.getDislikes().contains(user)) {
            article.getDislikes().remove(user);
        }
        if (article.getLikes().contains(user)) {
            article.getLikes().remove(user);
        }
        articleRepository.save(article);
        return ResponseEntity.ok().body("Unliked");
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteArticle(@PathVariable Integer id) {
        Article n = articleRepository.findById(id).get();
        articleRepository.delete(n);
        return "Deleted";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping(path="/user/{auteurId}")
    public @ResponseBody Iterable<Article> getArticleFromAuteur(Integer auteurId) {
        User auteur = userRepository.findById(auteurId).get();
        return articleRepository.findByAuteur(auteur);
    }
}
package com.spring.first.springproject.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.spring.first.springproject.Repository.ArticleRepository;

@Controller
@RequestMapping(path="/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewArticle (
        @RequestParam String auteur,
        @RequestParam String contenu,
        @RequestParam Date datePublication
    ) {
        Article n = new Article();
        n.setAuteur(auteur);
        n.setContenu(contenu);
        n.setDate_publication(datePublication);
        articleRepository.save(n);
        return "Saved";
    }

    @PutMapping(path="/update/{id}")
    public @ResponseBody String updateArticle (
        @PathVariable Integer id,
        @RequestParam String auteur,
        @RequestParam String contenu,
        @RequestParam Date datePublication
    ) {
        Article n = articleRepository.findById(id).get();
        n.setAuteur(auteur);
        n.setContenu(contenu);
        n.setDate_publication(datePublication);
        articleRepository.save(n);
        return "Modified";
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

    @GetMapping(path="/user/{auteur}")
    public @ResponseBody Iterable<Article> getArticleFromAuteur(String auteur) {
        return articleRepository.findByAuteur(auteur);
    }
}
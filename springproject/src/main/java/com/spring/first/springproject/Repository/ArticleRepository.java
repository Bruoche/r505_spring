package com.spring.first.springproject.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.first.springproject.Entity.Article;
import com.spring.first.springproject.Entity.User;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findByAuteur(User auteur);
}
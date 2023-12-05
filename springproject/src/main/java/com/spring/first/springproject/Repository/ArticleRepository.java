package com.spring.first.springproject.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.first.springproject.Entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findByAuteur(String auteur);
}
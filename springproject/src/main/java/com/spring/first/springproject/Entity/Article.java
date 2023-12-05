package com.spring.first.springproject.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity // This tells Hibernate to make a table out of this class
public class Article {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private Date date_publication;
  @ManyToOne
  private User auteur;
  private String contenu;
}
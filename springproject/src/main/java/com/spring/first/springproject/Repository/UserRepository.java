package com.spring.first.springproject.Repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.first.springproject.Entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
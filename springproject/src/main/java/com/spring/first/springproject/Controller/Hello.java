package com.spring.first.springproject.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    
    @GetMapping(path="bonjour")
    public static String helloWorld() {
        return "Bonjour le monde !";
    }
}

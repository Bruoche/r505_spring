package com.spring.first.springproject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    
    @RequestMapping(value="/bonjour")
    public static String helloWorld() {
        return "Bonjour le monde !";
    }
}

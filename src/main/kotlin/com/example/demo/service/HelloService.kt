package com.example.demo.service

import org.springframework.boot.SpringBootConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootConfiguration
class HelloService {
    @GetMapping("/hello-all")
    fun sayHelloAll() : String {
        return "hello-all"
    }

    @GetMapping("/hello-user")
    fun sayHelloUser() : String {
        return "hello-user"
    }

    @GetMapping("/hello-admin")
    fun sayHelloAdmin() : String {
        return "hello-admin"
    }
}
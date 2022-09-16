package org.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServerApplication.class,args);
        System.out.println("Hello world!");
    }
}
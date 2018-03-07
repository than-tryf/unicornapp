package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
*
* @SpringBootApplication is equivalent to using @Configuration @EnableAutoConfiguration @ComponentScan
*
*  */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

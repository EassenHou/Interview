package com.example.test2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test2Application {

    public static void main(String[] args) {
        SpringApplication.run(Test2Application.class, args);

        System.out.println(SigninController.cellPhoneNo("138 1234 1234"));
        System.out.println(SigninController.cellPhoneNo("13812345abc"));
        System.out.println(SigninController.cellPhoneNo("13812345678"));
        System.out.println(SigninController.cellPhoneNo("138 1234 5678"));
        System.out.println(SigninController.cellPhoneNo("98765432101"));
    }


}

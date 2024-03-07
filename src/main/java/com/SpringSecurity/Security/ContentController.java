package com.SpringSecurity.Security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ContentController {
   @GetMapping("/home")
    public String handleHome(){
        return "home";
    }
    @GetMapping("/admin/home")
    public String handleAdminhome(){
       return "admin";
    }
    @GetMapping("/user/home")
    public String handleuserhome(){
       return  "user";
    }
}

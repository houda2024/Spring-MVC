package com.example.NewBook.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
    @GetMapping("/errorPage")
    public String erroPage(){return "errorpage";}
}

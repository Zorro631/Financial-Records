package com.example.einnahmenausgaben;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IEController {
    @PostMapping("/add/income")
    public String addIncome(){
        return "success";
    }

}

package Babyak.babyak_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("apitest")
    @ResponseBody
    public String apitest(){
        return "test finish";
    }
}

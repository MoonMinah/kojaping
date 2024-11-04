package com.kosa.kojaping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error/403")
    public String error403() {
        return "error403";
    }
}

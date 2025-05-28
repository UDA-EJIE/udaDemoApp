package com.ejie.x21a.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/error")
    public String error(Model model) {
        return "error"; //"error.html"
    }
}

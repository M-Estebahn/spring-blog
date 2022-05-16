package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @GetMapping
    public String coffeeInfo() {
        return "views-lecture/coffee";
    }

    @PostMapping
    public String newsLetterSignup(@RequestParam(name = "email") String email, Model model) {
        model.addAttribute("email", email);
        return "views-lecture/coffee";
    }

    @GetMapping("/{roast}")
    public String coffeeInfo(@PathVariable String roast, Model model) {
        model.addAttribute("roast", roast);
        Coffee selection = new Coffee();
        selection.setRoast(roast);
        if (roast.equals("dark")) {
            selection.setOrigin("Colombia");
        } else if (roast.equals("medium")) {
            selection.setOrigin("New Guinea");
        } else {
            selection.setOrigin("Kenya");
        }
        model.addAttribute("selection", selection);
        return "views-lecture/coffee";
    }


}

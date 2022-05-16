package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/roll-dice")
public class RollController {
    @GetMapping
    public String dice() {
        return "/roll-dice";
    }


    @GetMapping("/{roll}")
    public String roll(@PathVariable String roll, Model model) {
        Random rand = new Random();

        int result = rand.nextInt(6 + 1) + 1;

        model.addAttribute("pick", result);

        model.addAttribute("roll",roll);
        return "/roll-dice";

    }
}


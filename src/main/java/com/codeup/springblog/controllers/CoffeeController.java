package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.repositories.CoffeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeRepository coffeeDao;


    public  CoffeeController(CoffeeRepository coffeeDao){
        this.coffeeDao=coffeeDao;
    }

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

    @GetMapping("/new")
    public String addCoffeeForm(){
        return "/coffees/new";
    }

    @PostMapping("/new")
    public String addCoffee(@RequestParam(name="brand") String brand,@RequestParam(name ="roast") String roast, @RequestParam(name="origin") String origin){
        Coffee coffee= new Coffee(roast,origin,brand);
        coffeeDao.save(coffee);
        return "coffees/new";
    }
}

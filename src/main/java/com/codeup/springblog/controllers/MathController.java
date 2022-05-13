package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @RequestMapping(path="/add100To/{number}" ,method= RequestMethod.GET)
    @ResponseBody
    public String addOneHudred(@PathVariable int number){
        return number +" + 100 = "+(number+100);
    }

    @RequestMapping(path = "/add/{num1}/to/{num2}",method = RequestMethod.GET)
    @ResponseBody
    public int add(@PathVariable int num1,@PathVariable int num2){
        return num1 + num2;
    }
    @RequestMapping(path="/subtract/{num1}/from/{num2}")
    @ResponseBody
    public int subtract(@PathVariable int num1,@PathVariable int num2) {
        return num1 - num2;
    }
    @RequestMapping(path="/multiply/{num1}/and/{num2}")
    @ResponseBody
    public int multiply(@PathVariable int num1,@PathVariable int num2) {
        return num1 * num2;
    }
    @RequestMapping(path="/divide/{num1}/by/{num2}")
    @ResponseBody
    public double multiply(@PathVariable double num1,@PathVariable double num2) {
        return num1 / num2;
    }
}

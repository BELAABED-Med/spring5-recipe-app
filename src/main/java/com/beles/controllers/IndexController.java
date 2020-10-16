package com.beles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    String getIndexPage(){
//        System.out.println("some fes hakmarr l3al am r tex t here");
        return "index";
    }

}

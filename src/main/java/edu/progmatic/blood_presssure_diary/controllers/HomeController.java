package edu.progmatic.blood_presssure_diary.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

     @RequestMapping(value = "/home", method = RequestMethod.GET)
     public String homePage() {

         return "home";
     }
     @GetMapping(value = "/rest/csref")
     public CsrfToken getCsrefToken(CsrfToken token){
         return token;
     }
 }
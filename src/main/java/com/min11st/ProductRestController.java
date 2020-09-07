package com.min11st;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.*;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class ProductRestController {

	@Autowired
	ProductRepository repo; 
	
	@RequestMapping("/showMessage.html") 
	public String index () {
		return "Spring Boot start" ; 
	}
	
	@RequestMapping("/index") 
	public ModelAndView index2 (ModelAndView modelAndView) {
		modelAndView.setViewName("index");
		modelAndView.addObject("product", repo.findAll());
		return modelAndView; 
	}

	@RequestMapping("/indexText") 
	public String index3 () {
		
		return repo.findByproductNameContaining("¹Ì´Ï").toString(); 
	}
}



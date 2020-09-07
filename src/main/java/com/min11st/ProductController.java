package com.min11st;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ch.qos.logback.classic.*;

@Controller
@RequestMapping("products")
public class ProductController {
	
	private final static Logger log=LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@ModelAttribute
	ProductForm setUpForm() {
		return new ProductForm();
	}
	
	@ModelAttribute
	 HttpRequestConfig setupConfig() {
		return new HttpRequestConfig();
	}
	
	
	
	@RequestMapping (method=RequestMethod.GET)
	public String list (Model model) {
		List <Product> productsss = productService.findAll();
		model.addAttribute("productsList", productsss);  // "products" go to thymeleaf attribute at list.html  
		return "products/list";
	}
	
	@RequestMapping (value="create", method=RequestMethod.POST) 
	public String create (@Validated ProductForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			log.info(result.getAllErrors().toString());
			return list(model);
		}
		Product product = new Product();
		BeanUtils.copyProperties(form, product);
		
		log.info("@@@ Create : FORM "+form.toStringWithDivider());
		log.info("@@@ Create : PRODUCT "+product.toStringWithDivider());
		
		productService.create(product);
		return "redirect:/products";
	}
	
	@RequestMapping (value="edit", params ="form", method=RequestMethod.GET)
	public String editForm(@RequestParam long id, ProductForm form) { 
		Product product = productService.findOne(id); 
		BeanUtils.copyProperties (product, form);
		
		log.info("@@@ Edit.GET : PRODUCT "+product.toStringWithDivider());
		log.info("@@@ Edit.GET : FORM "+form.toStringWithDivider());
		
		return "products/edit"; 
	}
	
	@RequestMapping (value="edit", method=RequestMethod.POST) 
	public String edit (@RequestParam long id, @Validated ProductForm form, BindingResult result) {
		if (result.hasErrors()) {
			return editForm(id, form);
		}
		Product product = new Product();
		BeanUtils.copyProperties(form, product);
		product.setId(id);
		productService.update(product);
		return "redirect:/products";
	}

	
	
	@RequestMapping (value="edit", params = "goToTop") 
	String goToTop() {
		return "redirect:/products";
	}

	@RequestMapping (value="delete", method=RequestMethod.POST) 
	String edit(@RequestParam long id) {
		productService.delete(id);
		return "redirect:/products";
	}
	
	@RequestMapping (value="import", method=RequestMethod.POST) 
	public String create (String keyword) {
		httpRequest.start(keyword);
		return "redirect:/products";
	}
 }

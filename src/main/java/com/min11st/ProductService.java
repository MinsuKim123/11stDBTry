package com.min11st;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private static final Logger log = LoggerFactory.getLogger(  ProductService.class);
	
	@Autowired
	ProductRepository productRepo;
	
	public void insertData() throws Exception {
		log.info("Http");
		
		/*
	    String getFrom11st = "openapi.11st.co.kr";
	    String key = "8b6bf8b77299b2e4565815cd9a7c174c";
	    String keyword = "Canston";
	    
  	  	Product[] products = httpRequest.fromXMLDocToProducts (httpRequest.getFullDataInXML (key, keyword));
  	  	
  	  	for (int i=0; i < products.length-1; i++ ) {
  	  		repo.save(products[i]);
  	  	}
  	  	log.info(">>>");
  	  	*/
	}
	
	public List<Product> findAll() {
		return productRepo.findAll();
	}
	
	public Product findOne(Long id) {
		System.out.println("@@@ Product FindOne @ProductService" + productRepo.findOne(id));
		return productRepo.findOne(id);
	}
	
	public Product create (Product product) {
		System.out.println("@@@ Product Create @ProductSercvice");
		return productRepo.save(product);
	}
	
	public Product update (Product product) {
		return productRepo.save(product);
	}
	
	public void delete (Long id) {
		productRepo.delete(id);
	}
	
}

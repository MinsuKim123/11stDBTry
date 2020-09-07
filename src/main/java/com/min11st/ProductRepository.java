package com.min11st;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.lang.Iterable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
 


public interface ProductRepository extends JpaRepository <Product, Long> {
	
	List<Product> findBySellerNick (@Param("nick") String nick);
	
	List<Product> findByproductNameContaining(@Param("word") String word);
	
	
	//@Query("select Product from Product where Product.PRODUCT_NAME like %?1%")
	//List<Product> findByCustomerQuery (String word);
	
	//List<Product> findAll();

	
}

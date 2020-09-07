package com.min11st;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



// Character Encoding Import
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.nio.charset.Charset;
import javax.servlet.Filter;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class mainApp {
	private static final Logger log=LoggerFactory.getLogger(mainApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(mainApp.class, args);
	}
	
	/*
	@Bean
	CommandLineRunner start(ProductService service) {
		return args -> {
			log.info("@@ ");
			// service.insertData();
			service.findAll().forEach(entry -> log.info(entry.toString()));
		};
	}
	*/
	
	
	/*
	 * Encoding Logic 인데 별로 쓸모가 없는 듯? 
	 *  
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
 
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        // characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    */

}

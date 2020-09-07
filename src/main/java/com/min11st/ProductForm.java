package com.min11st;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ch.qos.logback.classic.*;

//@Data
public class ProductForm {
	
	//private final static Logger log=LoggerFactory.getLogger(mainApp.class);

	
	@NotNull
	private long id;
	
	@NotNull //@Getter @Setter
	@Min(1) 	
	private long productCode ;
	
	@NotNull //@Getter @Setter
	@Size(min=3, max=127)
	private String productName;
	
	@NotNull
	@Min(1) @Max(1111111111)
	private long productPrice ;
	
	@NotNull 
	private String sellerNick ;
	
	//@NotNull 
	private String detailPageUrl ;

	
	public long getId( ) 				{ return id; }
	public long getProductCode( ) 		{ return productCode; }
	public String getProductName( ) 	{ return productName; }
	public long getProductPrice ( ) 	{ return productPrice; }
	public String getSellerNick ( ) 	{ return sellerNick; }
	public String detailPageUrl ( ) 	{ return detailPageUrl; }

	public void setId(long id) 							{ this.id=id; }
	public void setProductCode(long productCode) 		{ this.productCode=productCode; }
	public void setProductName(String productName) 		{ this.productName=productName; }
	public void setProductPrice(long productPrice) 		{ this.productPrice=productPrice; }
	public void setSellerNick(String sellerNick) 		{ this.sellerNick=sellerNick; }
	public void setDetailPageUrl(String dPageUrl) 		{ this.detailPageUrl=dPageUrl; }
	
	public String toStringWithDivider() {
		StringBuilder value = new StringBuilder();
		value.append("ProductCode:");
		value.append(productCode);
		value.append(", ProductName:");
		value.append(productName);
		value.append(", ProductPrice:");
		value.append(productPrice);
		value.append(", SellerNick:");
		value.append(sellerNick);
		value.append(", DetailPageURL:");
		value.append(detailPageUrl);
		value.append("------------------");
		value.append("\n");
		return value.toString();
	}


}

/**
private long id; 
private Long productCode; 
private String productName;
private double productPrice;
private String sellerNick;
private String detailPageUrl;
*/
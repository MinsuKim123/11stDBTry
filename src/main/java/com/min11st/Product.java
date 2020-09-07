package com.min11st;

/*
 * DB Schema :
 * Products
 * 	Total Count
 * 	Product
 * 	>	ProductCode
 * 	>	ProductName
 * 	>	ProductPrice
 * 		ProductImage
 * 		ProductImage100
 * 		ProductImage110
 * 		ProductImage120
 * 		ProductImage130
 * 		ProductImage140
 * 		ProductImage150
 * 		ProductImage170
 * 		ProductImage200
 * 		ProductImage250
 * 		ProductImage270
 * 		ProductImage300
 * 		Text1
 * 		Text2
 * 	>	SellerNick
 * 		Seller
 * 		SellerGrd
 * 		Rating
 * 	>	DetailPageUrl
 * 		SalePrice
 * 		Delivery
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Transient;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id; 
	private long productCode; 
	private String productName;
	private long productPrice;
	private String sellerNick;
	private String detailPageUrl;
	
	public Product (Long productCode, String productName, long productPrice, String sellerNick, String detailPageUrl) {
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.sellerNick = sellerNick;
		this.detailPageUrl = detailPageUrl;
	}
	
	public Product() {}
	
	public long 	getId () 			{		return id;		}
	public long 	getProductCode () 	{		return productCode;		}
	public String 	getProductName () 	{		return productName;		}
	public long 	getProductPrice ()	{		return productPrice;	}
	public String 	getSellerNick () 	{		return sellerNick;		}
	public String 	getDetailPageUrl () {		return detailPageUrl; 	}

	public void setId (Long id) 	{	this.id= id;	}
	public void setProductCode(Long productCode) 	{	this.productCode = productCode;	}
	public void setProductName(String productName)	{	this.productName = productName; }
	public void setProductPrice(long productPrice) {	this.productPrice= productPrice; }
	public void setSellerNick(String sellerNick)	{	this.sellerNick = sellerNick;	}
	public void setDetailPageUrl (String detailPageUrl) {this.detailPageUrl = detailPageUrl; }
	
	
	public String toString() {
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
		return value.toString();
	}
	
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

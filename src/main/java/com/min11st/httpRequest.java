package com.min11st;

import org.apache.http.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;

import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource; 

import org.json.*;

import java.io.*;
//import java.net.URL;
//import java.util.List;
//import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ch.qos.logback.classic.*;

import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
import java.net.URLEncoder;

public class httpRequest {
	
		 public static void setLoggingLevel(ch.qos.logback.classic.Level level) {
		    ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		    root.setLevel(level);
		 }
		
		  private final static Logger log=LoggerFactory.getLogger(mainApp.class);
		  private final static String openapi11st = "openapi.11st.co.kr";
		  private final static String key = "8b6bf8b77299b2e4565815cd9a7c174c";
		  private final static String localHost = "localhost/products";
		  private static String keyword = "»ï¼ºÀüÀÚ";
	
		  private static int totalCount ; 
		  private static int pageSize = 200;


		  /**
		   * Program Sequence 
		   *  1) [getFullDataInXML] Get N of product info from 11st in XML format
		   *  2) Convert XML to N of Product
		   *  3) Loop N times
		   *  4)   Put product to URI [postFullDataFromProductsToURI]  
		   *  		   * 
		   * @param args
		   */
	      public static void main (String [] args) {
	    	  setLoggingLevel(ch.qos.logback.classic.Level.TRACE);
    		  int pageNum=1;
    		  String sortCD="CP";
    		   
	    	  try {
		    	  do {
		    		  Product[] products = fromXMLDocToProducts (getFullDataInXML (key, keyword, pageSize, pageNum, sortCD)); // Read pageSize # of products
		    		  log.info("@@@ Products build success in length : "+products.length);
		    		  for (int i=0; i<products.length-1; i++) { 
		    			  postFullDataFromProductsToURI (products[i]);
		    			  log.info("@@@ pageNum=" + pageNum + ", pageSize="+pageSize + ", iCount="+i) ;
		    			  } 		    	  
		    		  pageNum++;
		    		  log.info("@@@ pageNum=" + pageNum + ", pageSize="+pageSize + ", totalCount="+totalCount) ;
		    	  } while ((pageNum-1) * pageSize < totalCount);
	    	  } catch (Exception e){ e.printStackTrace(); }
	      }
	      
	      public static void start (String keyword) {
	    	  setLoggingLevel(ch.qos.logback.classic.Level.TRACE);
    		  int pageNum=1;
    		  String sortCD="CP";
    		   
	    	  try {
		    	  do {
		    		  Product[] products = fromXMLDocToProducts (getFullDataInXML (key, keyword, pageSize, pageNum, sortCD)); // Read pageSize # of products
		    		  log.info("@@@ Products build success in length : "+products.length);
		    		  for (int i=0; i<products.length-1; i++) { 
		    			  postFullDataFromProductsToURI (products[i]);
		    			  log.info("@@@ pageNum=" + pageNum + ", pageSize="+pageSize + ", iCount="+i) ;
		    			  } 		    	  
		    		  pageNum++;
		    		  log.info("@@@ pageNum=" + pageNum + ", pageSize="+pageSize + ", totalCount="+totalCount) ;
		    	  } while ((pageNum-1) * pageSize < totalCount);
	    	  } catch (Exception e){ e.printStackTrace(); }
	      }
	      
	      

	      /**
	       * 
	       * @param key
	       * @param keyword
	       * @param pageSize
	       * @param pageNum
	       * @param sortCD
	       * @return pagesize # of records in XML Document  
	       * @throws Exception
	       * 
	       * Request url parameter = http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=8b6bf8b77299b2e4565815cd9a7c174c&apiCode=ProductSearch&keyword=test&pageNum=1&pageSize=50&sortCd=CP
	       * 
	       */
	      public static Document getFullDataInXML (String key, String keyword, int pageSize, int pageNum, String sortCD) throws Exception {
	    	  DefaultHttpClient httpclient = new DefaultHttpClient();
			  Document doc;
			  try {				  
			      HttpHost target = new HttpHost("openapi.11st.co.kr", 80, "http");
			      String requestParam = "/openapi/OpenApiService.tmall?" 
	  		  			+ "key=" + key 
	  		  			+ "&apiCode=ProductSearch"
	  		  			+ "&keyword=" + URLEncoder.encode(keyword)
	  		  			+ "&pageNum=" + pageNum
	  		  			+ "&pageSize=" + pageSize
	  		  			+ "&sortCd=" + "CP"; // 
				  
			      log.info("requestParam.euc-kr="+new String(requestParam.getBytes(), "EUC-KR"));
			      HttpGet getRequest = new HttpGet(requestParam);
			      getRequest.addHeader("Content-Type","charset=EUC-KR") ;
			      //getRequest.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:33.0) Gecko/20100101 Firefox/33.0");
			      HttpResponse httpResponse = httpclient.execute(target, getRequest);

			      String s = EntityUtils.toString(httpResponse.getEntity());
			      InputSource is = new InputSource(new StringReader(s));
			      log.info("@@@ [getFullDataInXML] The return from one request = "+s);
			      doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			      doc.getDocumentElement().normalize();
			      httpclient.getConnectionManager().shutdown();			      	
			  } catch (Exception e) {
		    	  System.out.println("DONT COME HERE------------------------------");
		    	  e.printStackTrace();
    	  		  throw e;
			  }   
		    return doc;
	      }
	      
	      
	      /**
	       * get XML Document and put to Product[] class 
	       *  
	       * @param doc
	       * @return
	       * @throws UnsupportedEncodingException
	       */
	      public static Product[] fromXMLDocToProducts (Document doc) throws UnsupportedEncodingException {
		      
	    	  totalCount = Integer.parseInt( ((Element)doc.getElementsByTagName("TotalCount").item(0)).getTextContent());
	    	  log.info("@@@ [fromXMLDocToProducts] Total Counter= "+totalCount);
	    	
	    	  doc.getDocumentElement().normalize();
		      NodeList nList = doc.getElementsByTagName("Product");
	    	  log.info("@@@ [fromXMLDocToProducts] nodeList.length= " + nList.getLength());
	    	  
		      Product[] products = new Product [nList.getLength()+1]; 
		      Long productCode = 0L; 
		      String productName = "";
		      Long productPrice = 0L;
		      String sellerNick = "";
		      String detailPageUrl = "";
		      
		      int loopCounter = (nList.getLength()<pageSize) ?  nList.getLength() : pageSize;
		      log.info("@@@ [fromXMLDocToProducts] LoopCounter = " + loopCounter);

		      for (int i=0; i<loopCounter; i++) {
			      Node nextNode = nList.item(i);
		    	  //if (nextNode.getNodeType() == Node.ELEMENT_NODE) {
	    		  Element e = (Element) nextNode;
	    		  productCode = Long.parseLong(e.getElementsByTagName("ProductCode").item(0).getTextContent());
	    		  productName = e.getElementsByTagName("ProductName").item(0).getTextContent();
	    		  productPrice = Long.parseLong(e.getElementsByTagName("ProductPrice").item(0).getTextContent());
	    		  sellerNick = e.getElementsByTagName("SellerNick").item(0).getTextContent();
	    		  detailPageUrl = e.getElementsByTagName("DetailPageUrl").item(0).getTextContent();
		    	  //}
		    	  
		    	  Product p = new Product (productCode, productName, productPrice, sellerNick, detailPageUrl);
		    	  log.info("@@@ [fromXMLDocToProducts] Products = "+p.toString());
		    	  products [i] = p;
		      }
		      return products;
		  }
	      
   	     // curl -i -X POST -H "Content-Type:application/hal+json" -d '{"productCode":"12345", "productName":"NAME", "productPrice":"100000", "sellerNick":"Seller", "detailPageUrl":"http:localhost1234"}' http://localhost:8080/products 
	      public static void postFullDataFromProductsToURI (Product product) throws Exception {
	    	  log.info("@@@ PostFullDataFromProductsToURI");
	    	  DefaultHttpClient httpclient = new DefaultHttpClient();
	    	  HttpPost httppost = new HttpPost("http://localhost:8080/api/products");
	    	  httppost.setHeader("Content-Type","application/hal+json;charset=UTF-8");
	    	  
	  			JSONObject json = new JSONObject(); 
	  			json.put("detailPageUrl", product.getDetailPageUrl());
	  			json.put("sellerNick", product.getSellerNick());
	  			json.put("productPrice", Long.toString(product.getProductPrice()));
	  			json.put("productName", product.getProductName());
	  			json.put("productCode", Long.toString(product.getProductCode()));

	  			log.info("@@@ [postFullDataProductsToURI] Json.getString(productName) = " + json.getString("productName"));
	  			log.info("@@@ [postFullDataProductsToURI] Json.toString = " + json.toString());

	  			StringEntity se = new StringEntity(json.toString(), "UTF-8");
	  			se.setContentEncoding(new BasicHeader("Content-Type","application/hal+json;charset=UTF-8"));
	  			httppost.setEntity(se);

	  			log.info("@@@ [postFullDataProductsToURI] httppost.toString = " + httppost.toString());
	  			log.info("@@@ [postFullDataProductsToURI] httppost.getParams().toString()= " + httppost.getParams().toString());
	  			log.info("@@@ [postFullDataProductsToURI] httppost.getEntity().toString() = " + httppost.getEntity().toString());

	  			/*
	  			byte[] b = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmemmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmerong".getBytes(); 
	  			httppost.getEntity().getContent().read(b);
	  			String s = new String(b, "UTF-8");
	  			log.info("@@@ [postFullDataProductsToURI] HTTPPost.getEntity =====> " + s);
	  			*/
	  			
	  			HttpResponse response = httpclient.execute(httppost);
	  			log.info("@@@ [postFullDataProductsToURI] Resoonse ====> " + response.toString()) ;
			    httpclient.getConnectionManager().shutdown();
	      }
	      
	      
	      /*
	      
	      public static String getFullDataInString (String key, String keyword) {
		    DefaultHttpClient httpclient = new DefaultHttpClient();
		    String returnString="";
		    try {
		      HttpHost target = new HttpHost(getFrom11st, 80, "http");
		      
		      // http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=[key]&apiCode= ProductSearch&keyword =[keyword]
		      String requestParam = "/openapi/OpenApiService.tmall?"
		    		  			+ "key=" + key 
		    		  			+ "&apiCode=ProductSearch"
		    		  			+ "&keyword=" + keyword;
		      
		      HttpGet getRequest = new HttpGet(requestParam);
		      		
		      HttpResponse httpResponse = httpclient.execute(target, getRequest);
		      HttpEntity entity = httpResponse.getEntity();

		      System.out.println(httpResponse.getStatusLine());
		      Header[] headers = httpResponse.getAllHeaders();

		      if (entity != null) {
		        returnString = EntityUtils.toString(entity); 
			    httpclient.getConnectionManager().shutdown();

		      }
		    } catch (Exception e) 	{	e.printStackTrace();
		    } 
		    return returnString;
		  }
	      */
	      
		}



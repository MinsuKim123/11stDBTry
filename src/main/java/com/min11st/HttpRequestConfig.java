package com.min11st;

public class HttpRequestConfig {

	private String keyword; 
	private Long totalCount;
	
	public String getKeyword ( ) {return keyword; }
	public Long totalCount ( ) {return totalCount; }
	
	public void setKeyword(String keyword) {this.keyword = keyword;}
	public void setTotalCount (Long totalCount) {this.totalCount = totalCount;}
}

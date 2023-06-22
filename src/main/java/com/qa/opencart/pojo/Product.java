package com.qa.opencart.pojo;

public class Product {
	
private String searchKey;
private String searchItem;
private int searchCount;
private String brand;
private String availability;
private String productTitle;
private String productPrice;

public Product(String searchKey, String searchItem, int searchCount) {
	this.searchKey = searchKey;
	this.searchItem = searchItem;
	this.searchCount = searchCount;
}



public Product(String searchKey, String searchItem, String brand, String availability, String productTitle, String productPrice) {
	this.searchKey= searchKey;
	this.searchItem= searchItem;
	this.brand = brand;
	this.availability = availability;
	this.productTitle = productTitle;
	this.productPrice = productPrice;
}



public String getSearchKey() {
	return searchKey;
}

public void setSearchKey(String searchKey) {
	this.searchKey = searchKey;
}

public String getSearchItem() {
	return searchItem;
}

public void setSearchItem(String searchItem) {
	this.searchItem = searchItem;
}

public int getSearchCount() {
	return searchCount;
}

public void setSearchCount(int searchCount) {
	this.searchCount = searchCount;
}



public String getBrand() {
	return brand;
}



public void setBrand(String brand) {
	this.brand = brand;
}



public String getAvailability() {
	return availability;
}



public void setAvailability(String availability) {
	this.availability = availability;
}



public String getProductTitle() {
	return productTitle;
}



public void setProductTitle(String productTitle) {
	this.productTitle = productTitle;
}



public String getProductPrice() {
	return productPrice;
}



public void setProductPrice(String productPrice) {
	this.productPrice = productPrice;
}



@Override
public String toString() {
	return "Product [searchKey=" + searchKey + ", searchItem=" + searchItem + ", searchCount=" + searchCount
			+ ", brand=" + brand + ", availability=" + availability + ", productTitle=" + productTitle
			+ ", productPrice=" + productPrice + "]";
}





}

package com.upnorway.pages;

public class Activity {
	
	String activityName="";
	String activtyType="";
	String price="";
	int items;
	String description ="";
	
	
	public String getActivityName() {
		return activityName.trim();
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivtyType() {
		return activtyType.trim();
	}
	public void setActivtyType(String activtyType) {
		this.activtyType = activtyType;
	}
	public String getPrice() {
		return price.trim();
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getItems() {
		return items;
	}
	public void setItems(int items) {
		this.items = items;
	}
	public String getDescription() {
		return description.trim();
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
   

}

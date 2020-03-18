package com.assignment.tinyurl.model;

import java.time.LocalDateTime;

/**
 * URL data model - id(tiny url),Long Url, Creation Time
 */
public class UrlModel {
	
	  private String id;
	  private String longUrl;
	  private LocalDateTime creationTime;
	  
	  public UrlModel() {
	  }

	  public UrlModel(String id, String longUrl, LocalDateTime creationTime) {
	    this.id = id;
	    this.longUrl = longUrl;
	    this.creationTime = creationTime;
	  }

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public String getlongUrl() {
	    return longUrl;
	  }

	  public void setlongUrl(String longUrl) {
	    this.longUrl = longUrl;
	  }

	  public LocalDateTime getcreationTime() {
	    return creationTime;
	  }

	  public void setcreationTime(LocalDateTime creationTime) {
	    this.creationTime = creationTime;
	  }
	  

}

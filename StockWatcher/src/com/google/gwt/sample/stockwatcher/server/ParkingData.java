package com.google.gwt.sample.stockwatcher.server;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ParkingData {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String limit;
	@Persistent
	private String rate;
	@Persistent
	private String description;
	@Persistent
	private Double lat;
	@Persistent
	private Double lon;
	

	public ParkingData() {}
	
	public ParkingData(String limit, String rate, String description, Double lat, Double lon) {
		this.setLimit(limit);
		this.setRate(rate);
		this.setDescription(description);
		this.setLat(lat);
		this.setLon(lon);
	}
	
	public Long getId() {
	    return this.id;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	
}

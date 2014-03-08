package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ParkingDataServiceAsync {

	public void addEntry(String limit, String rate, String desc, Double lat, Double lon,
			AsyncCallback<Void> callback);

	public void populateDatabaseFromURL(String url, AsyncCallback<Void> callback);

	public void removeEntry(String limit, String rate, String desc, Double lat, Double lon,
			AsyncCallback<Void> callback);

}

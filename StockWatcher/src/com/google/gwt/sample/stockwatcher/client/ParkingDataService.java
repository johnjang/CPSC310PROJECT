package com.google.gwt.sample.stockwatcher.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("parkingdata")
public interface ParkingDataService extends RemoteService {
  public void addEntry(String limit, String rate, String desc, Double lat, Double lon);
  public void removeEntry(String limit, String rate, String desc, Double lat, Double lon);
  public void populateDatabaseFromURL(String url);
}
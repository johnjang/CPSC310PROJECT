package com.google.gwt.sample.stockwatcher.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.gwt.sample.stockwatcher.client.ParkingDataService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ParkingDataServiceImpl extends RemoteServiceServlet implements
		ParkingDataService {

	private static final Logger LOG = Logger.getLogger(ParkingDataServiceImpl.class.getName());
	private static final PersistenceManagerFactory PMF =
	    JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	@Override
	public void addEntry(String limit, String rate, String desc, Double lat, Double lon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEntry(String limit, String rate, String desc, Double lat, Double lon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void populateDatabaseFromURL(String url) {
	    // get parkingdata from KML	    
	    KMLParser parser = new KMLParser();
		List<ParkingData> pdata = parser.getParkingDataFromURL(url);
		
		// store all parkingdata objects in datastore
	    PersistenceManager pm = getPersistenceManager();
	    try {
	        for (ParkingData pd : pdata) {
	            pm.makePersistent(pd);
	        }
	    } finally {
	        pm.close();
	    }
	}
	
	private PersistenceManager getPersistenceManager() {
	    return PMF.getPersistenceManager();
	}

}

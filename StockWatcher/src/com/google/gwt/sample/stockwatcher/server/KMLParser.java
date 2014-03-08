package com.google.gwt.sample.stockwatcher.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
import de.micromata.opengis.kml.v_2_2_0.LookAt;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

import org.apache.commons.io.IOUtils;

import java.io.StringWriter;


public class KMLParser {
	
	public static final String LIMIT_PREFIX = "Time Limit: ";
	public static final String RATE_PREFIX = "Rate: ";
	
	public KMLParser() {
		
	}
	
	public List<ParkingData> getParkingDataFromURL(String surl){
		ArrayList<ParkingData> pdata = new ArrayList<ParkingData>();		
		ZipInputStream zis = null;
        String kmlstring = null;
		try {
		    URL url = new URL(surl);
			InputStream kml = url.openStream();
			zis = new ZipInputStream(new BufferedInputStream(kml));
            InputStreamReader reader = new InputStreamReader(zis);
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				String filename = entry.getName();
				int i = filename.lastIndexOf('.');
				String extension = filename.substring(i);
				if (extension.equals(".kml")) {
				    char[] buf = new char[(int) entry.getSize()];
				    reader.read(buf);
				    kmlstring = new String(buf);				    
				}
			}
		}
        catch (MalformedURLException e1) {
            e1.printStackTrace();
            return null;
        }
		catch (IOException e) {
		    e.printStackTrace();
		    return null;
		}		
		Kml kml = Kml.unmarshal(kmlstring);
		Document doc = (Document) kml.getFeature();
		List<Feature> folders = doc.getFeature();
		Folder folder = (Folder) folders.get(0);
		List<Feature> features = folder.getFeature();
		for (Feature f : features) {
			try {
				Placemark p = (Placemark) f;
				String desc = p.getDescription();
				String[] descItems = desc.split("<br>");
				String limit = null;
				String rate = null;
				for (String s : descItems) {
					if (s.startsWith(LIMIT_PREFIX)) {
						limit = s.substring(LIMIT_PREFIX.length());
					}
					else if (s.startsWith(RATE_PREFIX)) {
						rate = s.substring(RATE_PREFIX.length());
					}
				}
				Point point = (Point) p.getGeometry();
				List<Coordinate> coords = point.getCoordinates();
				Coordinate c = coords.get(0);
				Double lat = c.getLatitude();
				Double lon = c.getLongitude();
				if ((desc != null) && (limit != null) && (rate != null) && (lat != null) && (lon != null)) {
					ParkingData pd = new ParkingData(limit, rate, desc, lat, lon);
					pdata.add(pd);
				}
			}
			catch (Exception e) {
				// faulty entry, do nothing
			}
		}
		System.out.println("Size: " + pdata.size());
		return pdata;
	}
	
}

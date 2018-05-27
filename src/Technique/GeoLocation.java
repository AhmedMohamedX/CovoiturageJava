/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;
import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import Technique.ServerLocation;
/**
 *
 * @author AH Info
 */
public class GeoLocation {
    
 
  public ServerLocation getLocation(String ipAddress) {

	File file = new File(
	    "C:\\Users\\AH Info\\Desktop\\PI-J\\GeoLiteCity.dat");
	return getLocation(ipAddress, file);

  }

  public ServerLocation getLocation(String ipAddress, File file) {

	ServerLocation serverLocation = null;

	try {

	serverLocation = new ServerLocation();

	LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
	Location locationServices = lookup.getLocation(ipAddress);
        getData gd = new getData();
	serverLocation.setCountryCode(locationServices.countryCode);
	serverLocation.setCountryName(gd.getCn());
	serverLocation.setRegion(locationServices.region);
	serverLocation.setRegionName(gd.getEn());
	serverLocation.setCity(gd.getC());
	serverLocation.setPostalCode(locationServices.postalCode);
	serverLocation.setLatitude(String.valueOf(locationServices.latitude));
	serverLocation.setLongitude(String.valueOf(locationServices.longitude));

	} catch (IOException e) {
		System.err.println(e.getMessage());
	}

	return serverLocation;

  }
}

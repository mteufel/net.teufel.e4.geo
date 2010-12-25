package net.teufel.e4.geo.impl;

import java.util.ArrayList;
import java.util.List;

import net.teufel.e4.geo.domain.Code;
import net.teufel.e4.geo.domain.Country;
import net.teufel.e4.geo.interfaces.IGeonameService;

public class GeonameServiceDummy implements IGeonameService {

	@Override
	public List<Code> getCodeByName(String name) {
		List<Code> sampleData = getSampleCodes();
		List<Code> ret = new ArrayList<Code>();
		for (Code plzResult : sampleData) {
			if (plzResult.getOrt().startsWith(name)) {
				ret.add(plzResult);
			}
		}
		
		if (ret.size() > 0)
			return ret;
				
		return sampleData;
	}

	@Override
	public List<Code> getCodeByNameAndCountry(String name, String country) {
		return getCodeByName(name);
	}

	@Override
	public List<Country> getAllCountries() {
		List<Country> dummy = new ArrayList<Country>();
		dummy.add(getCountry("DE"));
		return dummy;	
	}

	@Override
	public Country getCountry(String countryCode) {
		Country country = new Country();
		country.setCurrencyCode("DE");
		country.setCountryName("Deutschland");
		country.setCurrencyCode("EUR");
		
		return country;
	}

	@Override
	public List<Country> getNeighbours(Country country) {
		List<Country> dummy = new ArrayList<Country>();
		dummy.add(getCountry("DE"));
		return dummy;
	}
	
	private List<Code> getSampleCodes() {
		List<Code> data = new ArrayList<Code>();
		data.add(new Code("91785", "Pleinfeld", "DE", 49.1016, 10.9859, "BY", "Bayern", "", "Mittelfranken", "WUG", "Weissenburg-Gunzenhausen"));
		data.add(new Code("91792", "Ellingen", "DE", 49.0667, 10.9667, "BY", "Bayern", "", "Mittelfranken", "WUG", "Weissenburg-Gunzenhausen"));
		data.add(new Code("83471", "Berchtesgaden", "DE", 47.6301, 47.6301, "BY", "Bayern", "", "Oberbayern", "BGL", "Berchtesgaden"));
		data.add(new Code("10117", "Berlin", "DE", 52.51612, 13.38735, "BE", "Berlin", "", "Berlin", "B", "Berlin"));
		data.add(new Code("50126", "Bergheim", "DE", 50.95625, 6.63495, "NW", "Nordrhein-Westfalen", "", "Kšln", "BM", "Rhein-Erft-Kreis"));
		return data;
	}

}

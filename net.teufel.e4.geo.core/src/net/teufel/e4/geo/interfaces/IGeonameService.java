package net.teufel.e4.geo.interfaces;

import java.util.List;
import net.teufel.e4.geo.domain.Code;
import net.teufel.e4.geo.domain.Country;

public interface IGeonameService {

	List<Code> getCodeByName(String name);
	
	List<Code> getCodeByNameAndCountry(String name, String country);
	
	List<Country> getAllCountries();
	
	Country getCountry(String countryCode);
	
	List<Country> getNeighbours(Country country);
	
}

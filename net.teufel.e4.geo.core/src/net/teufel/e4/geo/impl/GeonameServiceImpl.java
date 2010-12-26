package net.teufel.e4.geo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.NodeMapper;

import net.teufel.e4.geo.domain.Code;
import net.teufel.e4.geo.domain.Country;
import net.teufel.e4.geo.domain.mappers.CodeNodeMapper;
import net.teufel.e4.geo.domain.mappers.CountryNodeMapper;
import net.teufel.e4.geo.domain.mappers.StringNodeMapper;
import net.teufel.e4.geo.interfaces.IGeonameService;

@SuppressWarnings("unchecked")
public class GeonameServiceImpl implements IGeonameService {
	
	@Override
	public List<Code> getCodeByName(String name) {
		String url = "http://ws.geonames.org/postalCodeSearch?placename_startsWith={name}";
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("name", name);
		return (List<Code>) doSearch(url, urlVariables, "//geonames/code", new CodeNodeMapper());
	}
	
	@Override
	public List<Code> getCodeByNameAndCountry(String name, String country) {
		String url = "http://ws.geonames.org/postalCodeSearch?placename_startsWith={name}&country={country}";
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("name", name);
		urlVariables.put("country", country);
		return (List<Code>) doSearch(url, urlVariables, "//geonames/code", new CodeNodeMapper());
	}	
	
	@Override
	public List<Country> getAllCountries() {
		String url = "http://ws.geonames.org/countryInfo?";
		return (List<Country>) doSearch(url, null, "//geonames/country", new CountryNodeMapper());
	}
	
	@Override
	public Country getCountry(String countryCode) {
		String url = "http://ws.geonames.org/countryInfo?country={country}";
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("country", countryCode);
		List<Country> countries = (List<Country>)  doSearch(url, urlVariables, "//geonames/country", new CountryNodeMapper());
		if (countries.size() == 0)
			return null;
		return countries.get(0);
	}
	
	@Override
	public List<Country> getNeighbours(Country country) {
		String url = "http://ws.geonames.org/neighbours?geonameId={geonameId}";
		Map<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("geonameId", Long.toString(country.getGeonameId()));
		List<String> countries = (List<String>)  doSearch(url, urlVariables, "//geonames/geoname", new StringNodeMapper("countryCode"));
		List<Country> neighbours = new ArrayList<Country>();
		for (String countryCode : countries) {
			neighbours.add(getCountry(countryCode));
		}
		return neighbours;
	}
	
	private List<?> doSearch(String url, Map<String,String> urlVariables, String xpath, NodeMapper mapper) {
		Source payload;
		if (urlVariables==null) {
			payload = new RestTemplate().getForObject(url, Source.class);
		} else {
			payload = new RestTemplate().getForObject(url, Source.class, urlVariables);
		}
		return new Jaxp13XPathTemplate().evaluate(xpath, payload, mapper);
	}
	
}

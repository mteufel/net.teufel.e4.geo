package net.teufel.e4.geo.domain.mappers;

import net.teufel.e4.geo.domain.Country;

import org.springframework.xml.xpath.NodeMapper;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CountryNodeMapper implements NodeMapper {

	@Override
	public Object mapNode(Node node, int i) throws DOMException {
		Element element = (Element) node;
		Country country = new Country();
		country.setCountryCode(element.getElementsByTagName("countryCode").item(0).getTextContent());
		country.setCountryName(element.getElementsByTagName("countryName").item(0).getTextContent());
		country.setIsoNumeric(Integer.parseInt(element.getElementsByTagName("isoNumeric").item(0).getTextContent()));
		country.setIsoAlpha3(element.getElementsByTagName("isoAlpha3").item(0).getTextContent());
		country.setFipsCode(element.getElementsByTagName("fipsCode").item(0).getTextContent());
		country.setContinent(element.getElementsByTagName("continent").item(0).getTextContent());
		country.setCapital(element.getElementsByTagName("capital").item(0).getTextContent());
		country.setAreaInSqKm(element.getElementsByTagName("areaInSqKm").item(0).getTextContent());
		country.setPopulation(Long.parseLong(element.getElementsByTagName("population").item(0).getTextContent()));
		country.setCurrencyCode(element.getElementsByTagName("currencyCode").item(0).getTextContent());
		country.setGeonameId(Long.parseLong(element.getElementsByTagName("geonameId").item(0).getTextContent()));
		return country;

	}

}

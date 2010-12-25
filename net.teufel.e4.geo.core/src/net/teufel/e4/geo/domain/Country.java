package net.teufel.e4.geo.domain;

public class Country {

	private long geonameId;
	private String countryCode;
	private String countryName;
	private int isoNumeric;
	private String isoAlpha3;
	private String fipsCode;
	private String continent;
	private String capital;
	private String areaInSqKm;
	private long population;
	private String currencyCode;

	public Country() {
	}

	public Country(String countryName) {
		this.countryName=countryName;
	}
	
	public long getGeonameId() {
		return geonameId;
	}
	public void setGeonameId(long geonameId) {
		this.geonameId = geonameId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getIsoNumeric() {
		return isoNumeric;
	}
	public void setIsoNumeric(int isoNumeric) {
		this.isoNumeric = isoNumeric;
	}
	public String getIsoAlpha3() {
		return isoAlpha3;
	}
	public void setIsoAlpha3(String isoAlpha3) {
		this.isoAlpha3 = isoAlpha3;
	}
	public String getFipsCode() {
		return fipsCode;
	}
	public void setFipsCode(String fipsCode) {
		this.fipsCode = fipsCode;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getAreaInSqKm() {
		return areaInSqKm;
	}
	public void setAreaInSqKm(String areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Override
	public String toString() {
		int maxLength = 15;
		int length = getCountryName().length();
		if (length > maxLength)
			length = maxLength;
		String countryName = (getCountryName().substring(0, length)).trim();
		if (getCountryCode() == null)
			return countryName;
		return getCountryCode() + " - " + countryName;
	}
	
	@Override
	public boolean equals(Object that) {
		if ( ((Country) that).getCountryName().equals(getCountryName()))
			return true;
		return false;

	}
}

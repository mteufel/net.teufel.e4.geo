package net.teufel.e4.geo.domain;

public class Code {
	
	private String plz;
	private String ort;
	private String countryCode;
	private String latAndLng;
	private String adminCode1;
	private String adminName1;
	private String adminCode2;
	private String adminName2;
	private String adminCode3;
	private String adminName3;
	
	public Code() {
	}

	public Code(String plz, String ort, String countryCode, double lng, double lat,
			String adminCode1, String adminName1, String adminCode2, String adminName2,
			String adminCode3, String adminName3) {
		this.plz = plz;
		this.ort = ort;
		this.countryCode = countryCode;
		this.adminCode1 = adminCode1;
		this.adminName1 = adminName1;
		this.adminCode2 = adminCode2;
		this.adminName2 = adminName2;
		this.adminCode3 = adminCode3;
		this.adminName3 = adminName3;
		this.latAndLng = lat + "+" + lng;
	}
	
	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLatAndLng() {
		return latAndLng;
	}
	
	public void setLatAndLng(String latAndLng) {
		this.latAndLng = latAndLng;
	}

	public String getAdminCode1() {
		return adminCode1;
	}

	public void setAdminCode1(String adminCode1) {
		this.adminCode1 = adminCode1;
	}

	public String getAdminName1() {
		return adminName1;
	}

	public void setAdminName1(String adminName1) {
		this.adminName1 = adminName1;
	}

	public String getAdminCode2() {
		return adminCode2;
	}

	public void setAdminCode2(String adminCode2) {
		this.adminCode2 = adminCode2;
	}

	public String getAdminName2() {
		return adminName2;
	}

	public void setAdminName2(String adminName2) {
		this.adminName2 = adminName2;
	}

	public String getAdminCode3() {
		return adminCode3;
	}

	public void setAdminCode3(String adminCode3) {
		this.adminCode3 = adminCode3;
	}

	public String getAdminName3() {
		return adminName3;
	}

	public void setAdminName3(String adminName3) {
		this.adminName3 = adminName3;
	}
	
	public String getGoogleMapsUrl(int width, int height) {
		return "http://maps.google.com/maps/api/staticmap?center=" + this.latAndLng + "&zoom=11&size=" + width + "x" + height + "&maptype=hybrid&sensor=false";
	}

	@Override
	public String toString() {
		return getPlz() + " - " + getOrt(); 
	}
	
}

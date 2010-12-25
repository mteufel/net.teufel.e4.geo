package net.teufel.e4.geo.domain.mappers;

import net.teufel.e4.geo.domain.Code;

import org.springframework.xml.xpath.NodeMapper;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CodeNodeMapper implements NodeMapper {

	@Override
	public Object mapNode(Node node, int i) throws DOMException {
		Element element = (Element) node;
		Code code=new Code();
		code.setPlz(element.getElementsByTagName("postalcode").item(0).getTextContent());
		code.setOrt(element.getElementsByTagName("name").item(0).getTextContent());
		code.setCountryCode(element.getElementsByTagName("countryCode").item(0).getTextContent());
		code.setAdminCode1(element.getElementsByTagName("adminCode1").item(0).getTextContent());
		code.setAdminName1(element.getElementsByTagName("adminName1").item(0).getTextContent());
		code.setAdminCode2(element.getElementsByTagName("adminCode2").item(0).getTextContent());
		code.setAdminName2(element.getElementsByTagName("adminName2").item(0).getTextContent());
		code.setAdminCode3(element.getElementsByTagName("adminCode3").item(0).getTextContent());
		code.setAdminName3(element.getElementsByTagName("adminName3").item(0).getTextContent());
		
		String latAndLng = element.getElementsByTagName("lat").item(0).getTextContent() + "+"
				 + element.getElementsByTagName("lng").item(0).getTextContent();
		code.setLatAndLng(latAndLng);
		
		return code;
	}

}

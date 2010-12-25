package net.teufel.e4.geo.domain.mappers;

import org.springframework.xml.xpath.NodeMapper;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class StringNodeMapper implements NodeMapper {

	private final String tagName;

	public StringNodeMapper(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public Object mapNode(Node node, int i) throws DOMException {
		Element element = (Element) node;
		return element.getElementsByTagName(this.tagName).item(0).getTextContent();
	}

}

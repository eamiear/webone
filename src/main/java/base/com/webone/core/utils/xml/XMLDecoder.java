package com.webone.core.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class XMLDecoder extends java.beans.XMLDecoder {
	public XMLDecoder(String beanXML) throws UnsupportedEncodingException {
		super(new ByteArrayInputStream(beanXML.getBytes("UTF-8"))); 
	}
}
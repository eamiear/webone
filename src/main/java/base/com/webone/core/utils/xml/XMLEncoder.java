package com.webone.core.utils.xml;

import java.io.OutputStream;

public class XMLEncoder extends java.beans.XMLEncoder {
	public XMLEncoder(OutputStream os) {
		super(os);
	}
}

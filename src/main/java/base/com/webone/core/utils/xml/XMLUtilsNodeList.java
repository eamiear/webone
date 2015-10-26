package com.webone.core.utils.xml;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class XMLUtilsNodeList implements NodeList {
	protected ArrayList<Node> alNodes;

	protected XMLUtilsNodeList() {
		this.alNodes = new ArrayList<Node>();
	}

	protected void addNode(Node node) {
		alNodes.add(node);
	}

	public Node item(int index) {
		return (Node) alNodes.get(index);
	}

	public int getLength() {
		return alNodes.size();
	}
}

package com.shf.dcs.boot;

import java.util.LinkedList;
import java.util.List;

public class TreeLevel {

	public static class Node {
		public Node(String data) {
			this.data = data;
		};

		public String data;
		public List<Node> childs = new LinkedList<Node>();
	}

	public static Integer level(Node tree, Node target) {
		return level(tree, target, 0);
	}

	private static Integer level(Node tree, Node target, int currentLevel) {
		Integer returnLevel = -1;
		if (tree.data.equals(target.data)) {
			returnLevel = currentLevel;
		} else {
			for (Node child : tree.childs) {
				if ((returnLevel = level(child, target, currentLevel + 1)) != -1) {
					break;
				}
			}
		}
		return returnLevel;

	}

	public static void main(String[] args) {

		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		//
		Node h = new Node("H");
		Node k = new Node("K");
		Node l = new Node("L");
		Node n = new Node("N");
		Node m = new Node("M");

		// childs of a:
		a.childs.add(b);
		a.childs.add(c);
		a.childs.add(d);

		// childs of b:
		b.childs.add(e);

		// childs of c:
		c.childs.add(f);
		c.childs.add(g);
		g.childs.add(h);
		h.childs.add(k);
		k.childs.add(l);
		l.childs.add(n);
		n.childs.add(m);
		
		// childs of d:
		// d.childs = null or simply d.childs.length() is 0
		Node target = new Node("M");
		Integer level = level(a, target);
		System.out.println("level [" + level + "]");

	}
}

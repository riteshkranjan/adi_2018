package com.uca.ds.graph;

public class ConnectedComponentsTest {

	public static void main(String[] args) {
		Graph g = new Graph(13);
		g.addEdge(0, 0);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(0, 6);
		g.addEdge(5, 3);
		g.addEdge(5, 4);
		g.addEdge(3, 4);
		g.addEdge(6, 4);
		g.addEdge(7, 8);
		g.addEdge(9, 10);
		g.addEdge(9, 11);
		g.addEdge(9, 12);
		g.addEdge(11, 12);
		ConnectedCompnents cc = new ConnectedCompnents(g);
		assert cc.isConnected(0, 5) == true;
		assert cc.isConnected(0, 9) == false;
		assert cc.isConnected(7, 8) == true;
		assert cc.isConnected(5, 7) == false;
		assert cc.isConnected(9, 7) == false;
		//System.out.println("all test cases passed");
	}
}

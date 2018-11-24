package com.uca.ds.graph;

class GraphUtilsTest {
	private static GraphUtils u = new GraphUtils();

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
		assert g.V()==13;
		assert g.E()==14;
		testDegree(g);
		testAvgDegree(g);
		testHasSelfLoop(g);
		testMaxDegree(g);
		testSelfLoopCount(g);
	}

	private static void testDegree(Graph g) {
		assert u.degree(g, 0) == 5;
		assert u.degree(g, 1) == 1;
		assert u.degree(g, 11) == 2;
	}

	private static void testHasSelfLoop(Graph g) {
		assert u.hasSelfLoop(g) == true;
	}

	private static void testSelfLoopCount(Graph g) {
		assert u.selfLoopCount(g) == 1:u.selfLoopCount(g);
	}

	private static void testMaxDegree(Graph g) {
		assert u.maxDegree(g) == 5:u.maxDegree(g);
	}

	private static void testAvgDegree(Graph g) {
		assert u.avgDegree(g) == 28/13;
	}
}

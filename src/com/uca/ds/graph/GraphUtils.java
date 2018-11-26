package com.uca.ds.graph;

public class GraphUtils {

	@SuppressWarnings("unused")
	public int degree(Graph g, int v) {
		int d = 0;
		for (int w : g.adj(v)) {
			d++;
		}
		return d;
	}

	public boolean hasSelfLoop(Graph g) {
		for (int v = 0; v < g.V(); v++)
			for (int w : g.adj(v)) {
				if (w == v)
					return true;
			}
		return false;
	}

	public int selfLoopCount(Graph g) {
		int count = 0;
		for (int v = 0; v < g.V(); v++)
			for (int w : g.adj(v)) {
				if (w == v)
					count++;
			}
		return count;
	}

	public int maxDegree(Graph g) {
		int max = 0;
		for (int v = 0; v < g.V(); v++) {
			max = Math.max(max, degree(g, v));
		}
		return max;
	}

	public float avgDegree(Graph g) {
		return (2 * g.E()) / g.V();
	}
	
	public boolean isEularTourPossible(Graph g) {
		DFS dfs = new DFS(g, 0);
		GraphUtils utils = new GraphUtils();
		for (int v = 0; v < g.V(); v++) {
			if (!dfs.isConnected(v))
				return false;
			if (utils.degree(g, v) % 2 != 0)
				return false;
		}
		return true;
	}
}

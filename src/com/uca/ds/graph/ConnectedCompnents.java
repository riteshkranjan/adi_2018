package com.uca.ds.graph;

public class ConnectedCompnents {
	private int[] CC;
	private boolean[] marked;

	public ConnectedCompnents(Graph g) {
		int code = 0;
		marked = new boolean[g.V()];
		CC = new int[g.V()];
		for (int i = 0; i < g.V(); i++) {
			if (!marked[i]) {
				marked[i] = true;
				CC[i] = code;
				dfs(g, i, code++);
			}
		}
	}

	private void dfs(Graph g, int v, int p) {
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				marked[w] = true;
				CC[w] = p;
				dfs(g, w, p);
			}
		}
	}

	public boolean isConnected(int v, int w) {
		return CC[v] == CC[w];
	}

}

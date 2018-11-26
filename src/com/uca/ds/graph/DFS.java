package com.uca.ds.graph;

import java.util.Stack;

public class DFS {
	private int[] edgeTo;
	private boolean[] marked;
	private final int s;
	private Graph g;

	public DFS(Graph g, int s) {
		this.s = s;
		this.g = g;
		edgeTo = new int[g.V()];
		marked = new boolean[g.V()];
		marked[s] = true;
		edgeTo[s] = s;
		dfs(s);
	}

	public boolean isConnected(int v) {
		return marked[v];
	}

	public Stack<Integer> pathTo(int v) {

		Stack<Integer> stack = new Stack<>();
		if (marked[v]) {
			while (v != s) {
				stack.push(v);
				v = edgeTo[v];
			}
			stack.push(s);
		}
		return stack;
	}

	public void dfs(int v) {
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				marked[w] = true;
				edgeTo[w] = v;
				dfs(w);
			}
		}
	}
}

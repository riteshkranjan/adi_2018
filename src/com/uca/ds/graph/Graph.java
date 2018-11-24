package com.uca.ds.graph;

public class Graph {
	private final int V;
	private Bag[] adj;
	private int E;

	public Graph(int V) {
		this.V = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag();
	}

	public void addEdge(int v, int w) {
		adj[v].enqueue(w);
		if (v != w)
			adj[w].enqueue(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public int E() {
		return E;
	}

	public int V() {
		return V;
	}

}

package com.uca.ds.graph;

import java.util.Stack;

public class DFSTest {

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
		DFS dfs = new DFS(g, 0);
		assert dfs.isConnected(1) == true;
		assert dfs.isConnected(7) == false;
		Stack<Integer> s = dfs.pathTo(6);
		assert s.size() == 5;
		assert s.pop()==0;
		assert s.pop()==5;
		assert s.pop()==3;
		assert s.pop()==4;
		assert s.pop()==6;
		
		s = dfs.pathTo(7);
		assert s.size() == 0;
	}

}

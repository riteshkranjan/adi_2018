package com.uca.ds.graph;
import java.util.Queue;
import java.util.Stack;
public class BFS{
  private int[] edgeTo;
  private boolean[] marked;
  private final int start;
  public BFS(Graph g, int start){
      this.edgeTo = new int[g.V()];
      this.marked = new boolean[g.V()];
      this.start= start;
      bfs(g);
  }

  private void bfs(Graph g){
      Queue<Integer> q = new java.util.LinkedList<>();
      q.add(start);
      marked[start] = true;
      edgeTo[start] = start;

      while(!q.isEmpty()){
          int v = q.poll();
          for(int w : g.adj(v)){
              if(!marked[w]){
                  q.add(w);
                  marked[w] = true;
                  edgeTo[w] = v;
              }
          }
      }
  }

  public boolean isConnected(int v){
      return marked[v];
  }

  public Stack<Integer> pathTo(int v){
      Stack<Integer> s = new Stack<>();
      while(v!=start){
        s.push(v);
        v = edgeTo[v];
      }
      s.push(start);
      return s;
  }






  
}
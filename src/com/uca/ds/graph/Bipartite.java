package com.uca.ds.graph;
public class Bipartite{
   private boolean[] marked;
   private static final boolean BLACK = true;
   private boolean isBipartite = true;
   private boolean[] color;
   public Bipartite(Graph g){
       marked = new boolean[g.V()];
       color = new boolean[g.V()];  
       color[0] = BLACK; 
       dfs(g, 0);
   }
   private void dfs(Graph g, int v){
      for(int w : g.adj(v)){
        if(isBipartite){
            if(marked[w] && color[v]==color[w]){
                isBipartite = false;
                return;
            }
            color[w] = !color[v];
            marked[w] = true;
            dfs(g,w);
        }
      }
   }

   public boolean isBipartite(){
       if(!isBipartite) return false;
       for(boolean b : marked){
           if(!b) return false;
       }
       return true;
   }
}
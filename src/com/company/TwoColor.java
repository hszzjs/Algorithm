package com.company;

/**
 * Created by Administrator on 2018/10/29 0029.
 */
public class TwoColor {
    private boolean[] color;
    private boolean[] marked;
    private boolean isTwoColorable=true;

    public TwoColor(Graph G){
        color=new boolean[G.V()];
        marked=new boolean[G.V()];
        for(int s=0;s<G.V();s++){
            if(!marked[s]){
                dfs(G,s);
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v]=true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                color[w]=!color[v];
                dfs(G,w);
            }else if(color[w]==color[v]) isTwoColorable=false;
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }
}

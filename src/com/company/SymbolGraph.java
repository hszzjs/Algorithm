package com.company;

/**
 * Created by Administrator on 2018/10/29 0029.
 */
public class SymbolGraph {
    private ST<String,Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String stream,String sp){
        st= new ST<String, Integer>() {
            @Override
            public void put(String s, Integer integer) {

            }

            @Override
            public Integer get(String s) {
                return null;
            }

            @Override
            public Integer delete(String s) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public String min() {
                return null;
            }

            @Override
            public String max() {
                return null;
            }

            @Override
            public String floor(String s) {
                return null;
            }

            @Override
            public String ceiling(String s) {
                return null;
            }

            @Override
            public int rank(String s) {
                return 0;
            }

            @Override
            public String select(int index) {
                return null;
            }

            @Override
            public Iterable<String> keys(String low, String high) {
                return null;
            }
        };
        In in =new In(stream);
        while (in.hasNextLine()){
            String[] a=in.readLine().split(sp);
            for(int i=0;i<a.length;i++)
                if(!st.contains(a[i]))
                    st.put(a[i],st.size());
        }
        keys=new String[st.size()];

        for(String name:st.keys())
            keys[st.get(name)]=name;

        G=new Graph(st.size());
        in=new In(stream);
        while (in.hasNextLine()){
            String[] a=in.readLine().split(sp);
            int v=st.get(a[0]);
            for(int i=1;i<a.length;i++)
                G.addEdge(v,st.get(a[i]));
        }
    }

    public boolean contains(String s){
        return st.contains(s);
    }

    public int index(String s){
        return st.get(s);
    }

    public String name(int v){
        return keys[v];
    }

    public Graph G(){
        return G;
    }
}

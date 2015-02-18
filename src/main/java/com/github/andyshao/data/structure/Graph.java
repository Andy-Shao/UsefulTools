package com.github.andyshao.data.structure;

import com.github.andyshao.lang.Cleanable;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Feb 18, 2015<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 * @param <D> data
 */
public interface Graph<D> extends Cleanable {

    public interface AdjList<DATA> {
        public static <D> AdjList<D> DEFAULT_ADJ_LIST(SetFactory<D> setFactory) {
            return new AdjList<D>() {
                private final Set<D> adjacent = setFactory.build();
                private D vertex;

                @Override
                public Set<D> adjacent() {
                    return this.adjacent;
                }

                @Override
                public D vertex() {
                    return this.vertex;
                }

                @Override
                public void vertex(D vertex) {
                    this.vertex = vertex;
                }
            };
        }

        public Set<DATA> adjacent();

        public DATA vertex();

        public void vertex(DATA vertex);
    }

    public class MyGraph<DATA> implements Graph<DATA> {
        protected SingleLinked<AdjList<DATA>> adjlists;
        protected int ecount;
        protected final SingleLinkedFactory<AdjList<DATA>> singleLinkedFactory;
        protected int vcount;

        public MyGraph(SingleLinkedFactory<AdjList<DATA>> singleLinkedFactory) {
            this.singleLinkedFactory = singleLinkedFactory;
            this.adjlists = this.singleLinkedFactory.build();
            this.vcount = 0;
            this.ecount = 0;
        }

        @Override
        public void clean() {
            // TODO Auto-generated method stub
            this.vcount = 0;
            this.ecount = 0;
            this.adjlists.clean();
        }

        @Override
        public com.github.andyshao.data.structure.Graph.AdjList<DATA> graph_adjlist(
            DATA data , com.github.andyshao.data.structure.Graph.AdjList<DATA> adjList) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public SingleLinked<com.github.andyshao.data.structure.Graph.AdjList<DATA>> graph_adjlists() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int graph_ecount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void graph_ins_edge(DATA data1 , DATA data2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void graph_ins_vertex(DATA data) {
            // TODO Auto-generated method stub

        }

        @Override
        public void graph_rem_edge(DATA data1 , DATA data2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void graph_rem_vertex(DATA data) {
            // TODO Auto-generated method stub

        }

        @Override
        public int graph_vcount() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public boolean match(DATA data1 , DATA data2) {
            // TODO Auto-generated method stub
            return false;
        }

    }

    public enum VertexColor {
        BLACK , GRAY , WHITE
    }

    public AdjList<D> graph_adjlist(final D data , AdjList<D> adjList);

    public SingleLinked<AdjList<D>> graph_adjlists();

    public int graph_ecount();

    public void graph_ins_edge(final D data1 , final D data2);

    public void graph_ins_vertex(final D data);

    public void graph_rem_edge(final D data1 , final D data2);

    public void graph_rem_vertex(D data);

    public int graph_vcount();

    public boolean match(D data1 , D data2);
}

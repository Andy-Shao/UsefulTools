package com.github.andyshao.data.structure;

import java.util.Comparator;
import java.util.HashSet;
import java.util.function.Supplier;

import com.github.andyshao.data.structure.convert.SetConvert;
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
        public static <D> AdjList<D> DEFAULT_ADJ_LIST(Supplier<Set<D>> setFactory) {
            return new AdjList<D>() {
                private final Set<D> adjacent = setFactory.get();
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
        private long actionAcount;
        protected Supplier<AdjList<DATA>> adjListFactory = () -> {
            return AdjList.DEFAULT_ADJ_LIST(() -> {
                return new SetConvert<DATA>().convert(new HashSet<DATA>());
            });
        };
        protected SingleLinked<AdjList<DATA>> adjlists;
        private final Comparator<DATA> comparator;
        protected int ecount;
        protected final Supplier<SingleLinked<AdjList<DATA>>> singleLinkedFactory;
        protected int vcount;

        public MyGraph(Comparator<DATA> comparator , Supplier<SingleLinked<AdjList<DATA>>> singleLinkedFactory) {
            this.singleLinkedFactory = singleLinkedFactory;
            this.comparator = comparator;
            this.adjlists = this.singleLinkedFactory.get();
            this.vcount = 0;
            this.ecount = 0;
            this.actionAcount = 0;
        }

        @Override
        public void clean() {
            this.vcount = 0;
            this.ecount = 0;
            this.adjlists.clean();
            this.actionAcount++;
        }

        @Override
        public Supplier<AdjList<DATA>> getAdjListFactory() {
            return this.adjListFactory;
        }

        @Override
        public Graph.AdjList<DATA> graph_adjlist(DATA data , Graph.AdjList<DATA> adjList) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public SingleLinked<Graph.AdjList<DATA>> graph_adjlists() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int graph_ecount() {
            return this.ecount;
        }

        @Override
        public void graph_ins_edge(DATA data1 , DATA data2) {
            // TODO Auto-generated method stub
            CycleLinkedElmt<AdjList<DATA>> element;

            //Do not allow insertion of an edge without both its vertices in the graph.
            element = this.search(data2);
            if (element == null) throw new GraphOperationException("Can't find out the data2 (" + data2
                + ") in the graph.");

            element = this.search(data1);
            if (element == null) throw new GraphOperationException("Can't find out the data1 (" + data1
                + ") in the graph.");

            //Insert the second vertex into the adjacency list of the first vertex.
            element.data().adjacent().set_insert(data2);

            //Adjust the edge count to account for the inserted edge.
            this.ecount++;
        }

        private CycleLinkedElmt<AdjList<DATA>> search(DATA data) {
            CycleLinkedElmt<AdjList<DATA>> result = null;

            for (CycleLinkedElmt<AdjList<DATA>> element = adjlists.head() ; element != null ; element = element.next())
                if (this.match(data , element.data().vertex())) {
                    result = element;
                    break;
                }

            return result;
        }

        @Override
        public void graph_ins_vertex(DATA data) {
            AdjList<DATA> adjlist;

            //Do not allow the insertion of duplicate vertices.
            if (this.search(data) != null) throw new GraphOperationException(
                "Do not allow the insertion of duplicate vertices.");

            //Insert the vertex.
            adjlist = this.adjListFactory.get();

            adjlist.vertex(data);
            this.adjlists.list_ins_next(this.adjlists.tail() , adjlist);

            //Adjust the vertex count to account for the inserted vertex.
            this.vcount++;
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
            return this.vcount;
        }

        @Override
        public boolean match(DATA data1 , DATA data2) {
            if (this.comparator.compare(data1 , data2) == 0) return true;
            else return false;
        }

        @Override
        public void setAdjListFactory(Supplier<Graph.AdjList<DATA>> adjListFactory) {
            if (this.actionAcount != 0) throw new SecurityException("Only could set field before insert & remove.");
            this.adjListFactory = adjListFactory;
        }
    }

    public enum VertexColor {
        BLACK , GRAY , WHITE
    }

    public Supplier<AdjList<D>> getAdjListFactory();

    public AdjList<D> graph_adjlist(final D data , AdjList<D> adjList);

    public SingleLinked<AdjList<D>> graph_adjlists();

    public int graph_ecount();

    public void graph_ins_edge(final D data1 , final D data2);

    public void graph_ins_vertex(final D data);

    public void graph_rem_edge(final D data1 , final D data2);

    public void graph_rem_vertex(D data);

    public int graph_vcount();

    public boolean match(D data1 , D data2);

    public void setAdjListFactory(Supplier<AdjList<D>> adjListFactory);
}

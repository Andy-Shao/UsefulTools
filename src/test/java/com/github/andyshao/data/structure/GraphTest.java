package com.github.andyshao.data.structure;

import java.util.PriorityQueue;
import java.util.Queue;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.data.structure.Graph.BfsVertex;
import com.github.andyshao.data.structure.SingleLinked;

public class GraphTest {
    private volatile Graph<String> graph;

    @Before
    public void before() {
        this.graph = Graph.<String> DEFAULT_GRAPH((obj1 , obj2) -> {
            return obj1.compareTo(obj2);
        } , () -> {
            return SingleLinked.DEFAULT_SINGLE_LINKED();
        });
    }

    @Test
    public void testBuildUntowardGraph() {
        final String node1 = "node1";
        final String node2 = "node2";
        final String node3 = "node3";
        final String node4 = "node4";
        final String node5 = "node5";
        final String node6 = "node6";
        this.graph.graph_ins_vertex(node1);
        this.graph.graph_ins_vertex(node2);
        this.graph.graph_ins_vertex(node3);
        this.graph.graph_ins_vertex(node4);
        this.graph.graph_ins_vertex(node5);
        this.graph.graph_ins_vertex(node6);
        Assert.assertThat(this.graph.graph_vcount() , Matchers.is(6));

        Graph.ADD_UNTOWARD_EDGE(this.graph , node1 , node2);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node1 , node3);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node2 , node3);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node2 , node4);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node3 , node5);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node4 , node5);
        Graph.ADD_UNTOWARD_EDGE(this.graph , node5 , node6);
        Assert.assertThat(this.graph.graph_ecount() , Matchers.is(14));
        
    }

    static final <DATA> Graph.BfsVertex<DATA> BUILD_BSFVERTEX(DATA data) {
        Graph.BfsVertex<DATA> result = Graph.BfsVertex.DEFAULT_BFS_VERTEX();
        result.data(data);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBsf() {
        final Graph.BfsVertex<String> node1 = BUILD_BSFVERTEX("node1");
        final Graph.BfsVertex<String> node2 = BUILD_BSFVERTEX("node2");
        final Graph.BfsVertex<String> node3 = BUILD_BSFVERTEX("node3");
        final Graph.BfsVertex<String> node4 = BUILD_BSFVERTEX("node4");
        final Graph.BfsVertex<String> node5 = BUILD_BSFVERTEX("node5");
        final Graph.BfsVertex<String> node6 = BUILD_BSFVERTEX("node6");
        Graph<Graph.BfsVertex<String>> graph = Graph.<Graph.BfsVertex<String>> DEFAULT_GRAPH((obj1 , obj2) -> {
            return obj1.data().compareTo(obj2.data());
        } , () -> {
            return SingleLinked.DEFAULT_SINGLE_LINKED();
        });
        graph.graph_ins_vertex(node1);
        graph.graph_ins_vertex(node2);
        graph.graph_ins_vertex(node3);
        graph.graph_ins_vertex(node4);
        graph.graph_ins_vertex(node5);
        graph.graph_ins_vertex(node6);
        Assert.assertThat(graph.graph_vcount() , Matchers.is(6));
        
        Graph.ADD_UNTOWARD_EDGE(graph , node1 , node2);
        Graph.ADD_UNTOWARD_EDGE(graph , node1 , node3);
        Graph.ADD_UNTOWARD_EDGE(graph , node2 , node3);
        Graph.ADD_UNTOWARD_EDGE(graph , node2 , node4);
        Graph.ADD_UNTOWARD_EDGE(graph , node3 , node5);
        Graph.ADD_UNTOWARD_EDGE(graph , node4 , node5);
        Graph.ADD_UNTOWARD_EDGE(graph , node5 , node6);
        Assert.assertThat(graph.graph_ecount() , Matchers.is(14));
        
        Queue<BfsVertex<String>> queue = new PriorityQueue<BfsVertex<String>>((obj1, obj2) -> {return Integer.compare(obj1.hops() , obj2.hops());});
        Graph.BREADTH_FIRST_SEARCH(graph , node6 , queue);
        Assert.assertThat(queue.poll() , Matchers.is(node6));
        Assert.assertThat(queue.poll() , Matchers.is(node5));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node4), Matchers.is(node3)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node4), Matchers.is(node3)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node1), Matchers.is(node2)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node1), Matchers.is(node2)));
    }
}

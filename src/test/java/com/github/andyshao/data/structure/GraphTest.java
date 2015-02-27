package com.github.andyshao.data.structure;

import java.util.PriorityQueue;
import java.util.Queue;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.andyshao.data.structure.Graph.BfsVertex;
import com.github.andyshao.data.structure.Graph.DfsVertex;

public class GraphTest {
    static final <DATA> Graph.BfsVertex<DATA> BUILD_BSFVERTEX(DATA data) {
        Graph.BfsVertex<DATA> result = Graph.BfsVertex.DEFAULT_BFS_VERTEX();
        result.data(data);
        return result;
    }

    static final <DATA> DfsVertex<DATA> BUILD_DFS_VERTEX(DATA data) {
        Graph.DfsVertex<DATA> result = DfsVertex.DEFAULT_DFS_VERTEX();
        result.data(data);
        return result;
    }

    private volatile Graph<String> graph;

    @Before
    public void before() {
        this.graph = Graph.<String> DEFAULT_GRAPH((obj1 , obj2) -> {
            return obj1.compareTo(obj2);
        } , () -> {
            return SingleLinked.DEFAULT_SINGLE_LINKED();
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBsf() {
        final Graph.BfsVertex<String> node1 = GraphTest.BUILD_BSFVERTEX("node1");
        final Graph.BfsVertex<String> node2 = GraphTest.BUILD_BSFVERTEX("node2");
        final Graph.BfsVertex<String> node3 = GraphTest.BUILD_BSFVERTEX("node3");
        final Graph.BfsVertex<String> node4 = GraphTest.BUILD_BSFVERTEX("node4");
        final Graph.BfsVertex<String> node5 = GraphTest.BUILD_BSFVERTEX("node5");
        final Graph.BfsVertex<String> node6 = GraphTest.BUILD_BSFVERTEX("node6");
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

        Queue<BfsVertex<String>> queue = new PriorityQueue<BfsVertex<String>>((obj1 , obj2) -> {
            return Integer.compare(obj1.hops() , obj2.hops());
        });
        Graph.BREADTH_FIRST_SEARCH(graph , node6 , queue);
        Assert.assertThat(queue.poll() , Matchers.is(node6));
        Assert.assertThat(queue.poll() , Matchers.is(node5));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node4) , Matchers.is(node3)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node4) , Matchers.is(node3)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node1) , Matchers.is(node2)));
        Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(node1) , Matchers.is(node2)));
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

    @SuppressWarnings("unchecked")
    @Test
    public void testDfs() {
        final DfsVertex<String> cs100 = GraphTest.BUILD_DFS_VERTEX("CS100");
        final DfsVertex<String> cs200 = GraphTest.BUILD_DFS_VERTEX("CS200");
        final DfsVertex<String> cs300 = GraphTest.BUILD_DFS_VERTEX("CS300");
        final DfsVertex<String> cs150 = GraphTest.BUILD_DFS_VERTEX("CS1500");
        final DfsVertex<String> ma100 = GraphTest.BUILD_DFS_VERTEX("MA100");
        final DfsVertex<String> ma200 = GraphTest.BUILD_DFS_VERTEX("MA200");
        final DfsVertex<String> ma300 = GraphTest.BUILD_DFS_VERTEX("MA300");
        Graph<DfsVertex<String>> graph = Graph.<DfsVertex<String>> DEFAULT_GRAPH((obj1 , obj2) -> {
            return obj1.data().compareTo(obj2.data());
        } , () -> {
            return SingleLinked.DEFAULT_SINGLE_LINKED();
        });
        graph.graph_ins_vertex(cs100);
        graph.graph_ins_vertex(cs200);
        graph.graph_ins_vertex(cs300);
        graph.graph_ins_vertex(ma100);
        graph.graph_ins_vertex(ma200);
        graph.graph_ins_vertex(ma300);
        Assert.assertThat(graph.graph_vcount() , Matchers.is(6));

        graph.graph_ins_edge(cs100 , cs200);
        graph.graph_ins_edge(cs200 , cs300);
        graph.graph_ins_edge(ma100 , cs300);
        graph.graph_ins_vertex(cs150);
        graph.graph_ins_edge(cs300 , ma300);
        graph.graph_ins_edge(ma100 , ma200);
        graph.graph_ins_edge(ma200 , ma300);
        Assert.assertThat(graph.graph_ecount() , Matchers.is(6));

        Queue<DfsVertex<String>> queue = new SimpleQueue<>();
        Graph.DEPTH_FIRST_SEARCH(graph , queue);
        DfsVertex<String> dfs = queue.peek();
        if(!dfs.data().equals(cs150.data())){
            Assert.assertThat(queue.poll() , Matchers.is(ma300));
            Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(ma200), Matchers.is(cs300)));
            Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(ma100), Matchers.is(cs200)));
        } else {
            Assert.assertThat(queue.poll() , Matchers.is(cs150));
            Assert.assertThat(queue.poll() , Matchers.is(ma300));
            Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(ma200), Matchers.is(cs300)));
            Assert.assertThat(queue.poll() , Matchers.anyOf(Matchers.is(ma100), Matchers.is(cs200)));
        }
    }
}

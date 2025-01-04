package org.example.jan04;

import java.util.ArrayList;
import java.util.TreeSet;

public class SSSP {
    public static void main(String[] args) {
        GraphDJ graph = new GraphDJ(5);
        graph.insert(0,1,2);
        graph.insert(1,3,2);
        graph.insert(0,2,30);
        graph.insert(2,3,30);
        graph.insert(3,0,20);
        graph.insert(1,4,20);
        graph.insert(3,4,10);
        graph.insert(2,4,4);

        graph.SSSP(0);

    }
}


class GraphDJ {
    ArrayList<Edge> [] adjList;
    int n;

    public GraphDJ(int n) {
        this.n = n;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int s, int d, int w) {
        Edge edge = new Edge(s,d,w);
        adjList[s].add(edge);
    }

    void SSSP(int s) {
        Node [] nodes = new Node[n];

        for (int i=0;i<n;i++) {
            nodes[i] = new Node(i,Integer.MAX_VALUE);
        }

        nodes[s].dis=0;
        TreeSet<Node> nodeM  = new TreeSet<>();
        nodeM.add(nodes[s]);

        while (!nodeM.isEmpty()) {
            Node top = nodeM.first();
            nodeM.remove(top);

            for (Edge neigh: adjList[top.index]) {
                int d = neigh.d;

                if (nodes[d].dis > top.dis+neigh.w) {
                    nodes[d].dis = top.dis+neigh.w;
                    nodeM.add(nodes[d]);
                }
            }
        }

        for (int i=0;i<n;i++) {
            System.out.println(i+", "+nodes[i].dis);
        }


    }

}


class Node implements Comparable<Node>{
    int index;
    long dis;

    public Node(int index, long dis) {
        this.index = index;
        this.dis = dis;
    }

    @Override
    public int compareTo(Node o) {
        if (this.dis != o.dis) {
            return Long.compare(this.dis, o.dis);
        }

        return Integer.compare(this.index, o.index);
    }
}

//13:30 - 13:45
class Edge {
    int s;
    int d;
    int w;

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }
}
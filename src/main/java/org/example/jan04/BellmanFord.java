package org.example.jan04;

import java.util.ArrayList;

public class BellmanFord {
    public static void main(String[] args) {
        GraphB graph = new GraphB(5);
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


//13:45 - 13:56
class GraphB {
    ArrayList<EdgeB> edges;
    int numN;

    public GraphB(int n) {
        this.numN=n;
        edges = new ArrayList<>();
    }

    void insert(int s, int d, int w) {
        EdgeB edgeB = new EdgeB(s,d,w);
        edges.add(edgeB);
    }

    void SSSP(int s) {
        long[]sssp = new long[numN];

        for (int i=0;i<numN;i++) {
            sssp[i] = Integer.MAX_VALUE;
        }

        sssp[s]=0;

        for (int i=0;i<numN;i++) {
            for (EdgeB edgeB: edges) {
                sssp[edgeB.d] = Math.min(sssp[edgeB.d],sssp[edgeB.s]+edgeB.w);
            }
        }

        for (int i=0;i<numN;i++) {
            System.out.println(i+"\t"+sssp[i]);
        }

    }
}



class EdgeB {
    public EdgeB(int s, int d, long w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }

    int s;
    int d;
    long w;
}
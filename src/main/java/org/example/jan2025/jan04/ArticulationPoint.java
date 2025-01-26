package org.example.jan2025.jan04;

import java.util.ArrayList;

public class ArticulationPoint {
    public static void main(String[] args) {
        GraphAP graph= new GraphAP(6);

        graph.insert(0,1);
        graph.insert(0,2);
        graph.insert(1,2);
        graph.insert(1,3);
        graph.insert(2,3);
        graph.insert(3,4);
        graph.insert(3,5);
        graph.insert(4,5);
        graph.DFS(0,-1);

        graph.print();
    }

}


// 14:42
class GraphAP {
    ArrayList<Integer> [] adjList;
    int n;
    int [] tIn;
    int [] tOut;
    int []minTime;
    boolean[]isArticulationPoint;
    int clock;


    public GraphAP(int n) {
        this.n = n;
        tIn = new int[n];
        tOut = new int[n];
        minTime = new int[n];
        isArticulationPoint = new boolean[n];
        clock=0;
        adjList = new ArrayList[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    void print() {
        System.out.println("Adj List");
        for (int i=0;i<n;i++) {
            System.out.print(i+": ");
            for (int neigh: adjList[i]) {
                System.out.print(neigh+", ");
            }
            System.out.println();
        }

        System.out.println("I\ttIn\ttOut\tmin\tiAP");
        for (int i=0;i<n;i++) {
            System.out.println(i+"\t"+tIn[i]+"\t"+tOut[i]+"\t\t"+minTime[i]+"\t"+isArticulationPoint[i]);
        }
    }

    void DFS(int n, int p) {
        if (tIn[n]>0) {
            return;
        }
        tIn[n]=++clock;

        int ccc=0;

        minTime[n]=tIn[n];

        for (int neighbour: adjList[n]) {
            if (neighbour != p && tIn[neighbour]==0) {
                ccc++;

                DFS(neighbour,n);
                if (p==-1 && ccc>1) {
                    isArticulationPoint[n]=true;
                }else if ( p!=-1 && minTime[neighbour] >= tIn[n]) {
                    isArticulationPoint[n]=true;
                }
            }
        }

        for (int nei: adjList[n]) {
            if (nei != p) {
                minTime[n] = Math.min(minTime[n],minTime[nei]);
            }
        }
        tOut[n]=++clock;
    }
}
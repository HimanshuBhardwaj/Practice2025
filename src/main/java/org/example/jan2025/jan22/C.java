package org.example.jan2025.jan22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/*
Name: Himanshu Bhardwaj
Date: 22-01-2025
*/
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            Graph graph = new Graph(n);

            for (int i=0;i<n-1;i++) {
                String [] str = br.readLine().split(" ");
                int s = Integer.parseInt(str[0])-1;
                int d = Integer.parseInt(str[1])-1;

                graph.insert(s,d);
            }
            pw.append(graph.maxNumberOFConnectedComponent()+"\n");
        }

        pw.flush();
        pw.close();
    }
}


class Graph {
    TreeSet<Integer> adjList[];
    int n;

    public Graph(int n) {
        this.n = n;
        adjList = new TreeSet[n];

        for (int i=0;i<n;i++) {
            adjList[i] = new TreeSet<>();
        }
    }

    void insert(int s, int d) {
        adjList[s].add(d);
        adjList[d].add(s);
    }

    int maxNumberOFConnectedComponent() {
        if (n==2) {
            return 0;
        }

        if (n==3) {
            return 1;
        }

        int degree [] = new int[n];

        for (int i=0;i<degree.length;i++) {
            degree[i] = adjList[i].size();
        }

        Arrays.sort(degree);

        int firstNode = nodeWithDegree(degree[degree.length-1],-1);

        removeNode(firstNode);


        for (int i=0;i<degree.length;i++) {
            degree[i] = adjList[i].size();
        }

        Arrays.sort(degree);

        int secondNode = nodeWithDegree(degree[degree.length-1],firstNode);

        removeNode(secondNode);


        return maxNumberConnectedComponenets(firstNode, secondNode);

    }

    private int nodeWithDegree(int d, int nA) {
        for (int i=0;i<n;i++) {
            if (i!= nA && adjList[i].size()==d && !adjucent(nA,i)) {
                return i;
            }
        }

        for (int i=0;i<n;i++) {
            if (i!= nA && adjList[i].size()==d) {
                return i;
            }
        }

        return -1;
    }

    private boolean adjucent(int nA, int i) {
        if (nA==-1) {
            return false;
        }
        return adjList[nA].contains(i) || adjList[i].contains(nA);
    }

    private int maxNumberConnectedComponenets(int firstNode, int secondNode) {
        if (n==2) {
            return 0;
        }

        boolean[] visited = new boolean[n];
        visited[firstNode]=true;
        visited[secondNode]=true;

        int cc=0;

        for (int i=0;i<n;i++) {
            if (!visited[i]) {
                cc++;
                BFS(i,visited);
            }
        }

        return cc;
    }

    private void BFS(int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node]=true;

        while (!queue.isEmpty()) {
            int top = queue.poll();

            for (int nei: adjList[top]) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    queue.add(nei);
                }
            }
        }
    }

    private void removeNode(int node) {
        for (int i=0;i<n;i++) {
            if (i==node) {
                adjList[i] = new TreeSet<>();
            } else {
                adjList[i].remove(node);
            }
        }
    }
}
package org.example.jan2025.jan04;

public class APSP {
    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.insert(0,1,1);
        graph.insert(0,2,2);
        graph.insert(1,3,2);
        graph.insert(2,3,2);
        graph.insert(3,0,4);

        graph.APSP();
    }
}

//13:22 -13:31
class Graph {
    long [][] adjMat;
    int numN;

    public Graph(int n) {
        this.numN = n;
        adjMat = new long[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                adjMat[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    void insert(int s, int d, int w) {
        adjMat[s][d]=w;
    }

    void APSP() {
        for (int k=0;k<numN;k++){
        for (int i=0;i<numN;i++) {
            for (int j=0;j<numN;j++) {
                    adjMat[i][j] = Math.min(adjMat[i][j],adjMat[i][k]+adjMat[k][j]);
                }
            }
        }

        for (int i=0;i<numN;i++) {
            System.out.print(i+": ");
            for (int j=0;j<numN;j++) {
                System.out.print("("+j+" "+adjMat[i][j]+"), ");
            }
            System.out.println();
        }
    }
}

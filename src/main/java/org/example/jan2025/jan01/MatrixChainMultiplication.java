package org.example.jan2025.jan01;

import java.util.TreeMap;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        MCMI mcmi = new MatrixChainMultiplicationBF();
        MCMI mcmiTD = new MatrixChainMiltiplicationTopDown();
        MCMI mcmiBU = new MatrixChainMultiplicationBottumUp();
        int [] matS = {2,1,3,4};
        System.out.println(mcmi.minimumCost(matS));
        System.out.println(mcmiTD.minimumCost(matS));
        System.out.println(mcmiBU.minimumCost(matS));

        int []matS1 = {1,2,3,4,3};
        System.out.println(mcmi.minimumCost(matS1));
        System.out.println(mcmiTD.minimumCost(matS1));
        System.out.println(mcmiBU.minimumCost(matS1));

        int [] matS2 = {3,4};
        System.out.println(mcmi.minimumCost(matS2));
        System.out.println(mcmiTD.minimumCost(matS2));
        System.out.println(mcmiBU.minimumCost(matS2));
    }
}

class MatrixChainMultiplicationBottumUp implements MCMI {

    @Override
    public long minimumCost(int[] matS) {
        long [][]dp = new long[matS.length][matS.length];
        for (int i=0;i<dp.length;i++) {
            for (int j=i+2;j<dp.length;j++) {
                dp[i][j]=Long.MAX_VALUE;
            }
        }

        for (int len=2;len<matS.length;len++) {
            for (int i=0;i+len<matS.length;i++) {
                int s=i;
                int e=i+len;

                for (int k=s+1;k<e;k++) {
                    dp[s][e]= Math.min(dp[s][e],dp[s][k]+dp[k][e]+matS[s]*matS[k]*(long)matS[e]);
                }
            }
        }

        return dp[0][matS.length-1];
    }
}

class MatrixChainMiltiplicationTopDown implements MCMI {

    @Override
    public long minimumCost(int[] matS) {
        TreeMap<String, Long> table = new TreeMap<>();
        return minimumCostHelper(0,matS.length-1,matS,table);
    }

    private long minimumCostHelper(int s, int e, int[] matS, TreeMap<String, Long> table) {
        if (s<0 || s>=e || e>=matS.length || e==s+1) {
            return 0;
        }

        if (s==e-2) {
            return ((long) matS[s]) *matS[s+1]*matS[s+2];
        }
        String key = s+"|"+e;

        if (table.containsKey(key)) {
            return table.get(key);
        }
        long minCost = Long.MAX_VALUE;
        for (int i=s+1;i<e;i++) {
            minCost = Math.min(minCost, minimumCostHelper(s,i,matS,table)+minimumCostHelper(i,e,matS,table)+matS[s]*matS[i]*(long)matS[e]);
        }
        table.put(key,minCost);

        return minCost;
    }
}

class MatrixChainMultiplicationBF implements MCMI {

    @Override
    public long minimumCost(int[] matS) {
        return minimumMulCostHelper(0,matS.length-1,matS);
    }

    private long minimumMulCostHelper(int s, int e, int[] matS) {
        if (s<0 || e>=matS.length || s>=e || s==e-1) {
            return 0;
        }

        if (s == e-2) {
            return (long)matS[s]*matS[s+1]*matS[e];
        }
        long minCost = Long.MAX_VALUE;
        for (int i=s+1;i<e;i++) {
            minCost = Math.min(minCost, minimumMulCostHelper(s,i,matS)+minimumMulCostHelper(i,e,matS)+matS[s]*matS[i]*(long)matS[e]);
        }

        return minCost;
    }
}

interface MCMI {
    long minimumCost(int [] matS);
}

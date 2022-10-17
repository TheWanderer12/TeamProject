package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.mybatisimpl.CityDAO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

public class FloydsAlgorithm {
    final static NumberFormat nf = new DecimalFormat("#0.00");
    public static int[][] path;

    public static void initializePath(double[][] graph){
        path = new int[graph.length][graph.length];
        for(int i = 1; i != path.length; i++)
            for(int j = 0; j != path.length; j++)
                path[j][i] = i;
    }
    public static void printPath(int from, int to, double graph[][]) {
        LinkedList<Integer> list = new LinkedList<>();
        int temp;
        temp = to;
        while (temp != path[from][temp]) {
            list.addFirst(temp);
            temp = path[from][temp];
        }
        list.addFirst(temp);
        System.out.print((from));
        list.forEach(integer -> System.
                out.print("->" + (integer)));
        System.out.print(" distance = " + graph[from][to] + "\n");
    }


    public static double[][] floydWarshall(double graph[][]) {
//        printMatrix(graph);
        int length = graph.length;
        double matrix[][] = new double[length][length];
        int i, j, k;

        for (i = 0; i < length; i++)
            for (j = 0; j < length; j++)
                matrix[i][j] = graph[i][j];

        printMatrix(matrix);
        // Adding vertices individually
//        for (k = 0; k < length; k++) {
//            for (i = 0; i < length; i++) {
//                for (j = 0; j < length; j++) {
//                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][k]+matrix[k][j]);
//                    path[k][j] = i;
//                }
//            }
//            System.out.print("\nMatrix N"+k + ": ");
//            printMatrix(matrix);
//        }
        for (i = 0; i < length; i++){
            for (j = 0; j < length; j++){
                for (k = 0; k < length; k++){
                    if (graph[i][j] + graph[j][k] < graph[i][k]) {
                        graph[i][k] = graph[i][j] + graph[j][k];
                        path[i][k] = j;
                    }
                }
            }
            System.out.print("\nMatrix N"+i + ": ");
            printMatrix(matrix);
        }



        return matrix;
    }

    public static void printMatrix(double matrix[][]) {
        System.out.println();
        for (int i = 0; i < matrix.length; ++i) {
            System.out.print(i);
            for (int j = 0; j < matrix.length; ++j) {
                System.out.print(String.format("%15s",nf.format(matrix[i][j]) + "  "));
            }
            System.out.println();
        }
    }


}

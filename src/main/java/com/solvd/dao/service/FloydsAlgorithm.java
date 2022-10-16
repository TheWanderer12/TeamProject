package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.mybatisimpl.CityDAO;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FloydsAlgorithm {

    final static NumberFormat nf = new DecimalFormat("#0.00");
    // Implementing floyd warshall algorithm
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
        for (k = 0; k < length; k++) {
            for (i = 0; i < length; i++) {
                for (j = 0; j < length; j++) {
                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][k]+matrix[k][j]);
                }
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    public static void printMatrix(double matrix[][]) {
        System.out.println();
        for (int j = 1; j <=matrix.length; ++j) {
            System.out.print(String.format("%14.5s",j));
        }
        System.out.println();
        for (int i = 0; i < matrix.length; ++i) {
            System.out.print(i+1);
            for (int j = 0; j < matrix.length; ++j) {
                System.out.print(String.format("%15s",nf.format(matrix[i][j]) + "  "));
            }
            System.out.println();
        }
    }


}

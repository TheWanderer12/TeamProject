package com.solvd.dao.service;

import com.solvd.dao.models.City;
import com.solvd.dao.mybatisimpl.CityDAO;

public class FloydsAlgorithm {


    // Implementing floyd warshall algorithm
    public static double[][] floydWarshall(double graph[][]) {
        int length = graph.length;
        double matrix[][] = new double[length][length];
        int i, j, k;

        for (i = 0; i < length; i++)
            for (j = 0; j < length; j++)
                matrix[i][j] = graph[i][j];

        // Adding vertices individually
        for (k = 0; k < length; k++) {
            for (i = 0; i < length; i++) {
                for (j = 0; j < length; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
        printMatrix(matrix);
        return matrix;
    }

    public static void printMatrix(double matrix[][]) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix.length; ++j) {
                if (matrix[i][j] < 0)
                    System.out.print("-1 ");
                else
                    System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }


}

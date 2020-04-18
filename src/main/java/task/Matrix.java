package task;

public class Matrix {
    public static double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        for (int row = 0; row < c.length; row++) {
            for (int col = 0; col < c[row].length; col++) {
                for (int i = 0; i < b.length; i++) {
                    c[row][col] += a[row][i] * b[i][col];
                }
            }
        }
        return c;
    }

    public static void print(double[][] a) {
        int rows = a.length;
        int cols = a[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0;j < cols; ++j) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}

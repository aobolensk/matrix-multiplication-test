package task;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    @Test
    public void simpleMultiplicationTest() {
        double[][] a = {
            {1, 2},
            {3, 4}
        };
        double[][] b = {
            {1, 2},
            {3, 4}
        };
        double[][] expected = {
            {7, 10},
            {15, 22}
        };
        double[][] answer = Matrix.multiply(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    @Test
    public void simpleAdditionTest() {
        double[][] a = {
            {1, 2},
            {3, 4}
        };
        double[][] b = {
            {1, 2},
            {3, 4}
        };
        double[][] expected = {
            {2, 4},
            {6, 8}
        };
        double[][] answer = Matrix.add(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    @Test
    public void simpleSubtractionTest() {
        double[][] a = {
            {1, 2},
            {3, 4}
        };
        double[][] b = {
            {1, 2},
            {3, 4}
        };
        double[][] expected = {
            {0, 0},
            {0, 0}
        };
        double[][] answer = Matrix.subtract(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    @Test
    public void canReadMatricesFromFile() {
        double[][] a = Matrix.read("data/matrix1.txt");
        double[][] b = Matrix.read("data/matrix2.txt");
        double[][] expected = Matrix.read("data/matrix3.txt");
        double[][] answer = Matrix.multiply(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    @Test
    public void canHandleNonExistentFile() {
        double[][] a = Matrix.read("data/matrix0.txt");
        Assertions.assertTrue(Arrays.deepEquals(null, a));
    }

    @Test
    public void canHandleIncorrectFileFormat() {
        double[][] a = Matrix.read("data/incorrect.txt");
        Assertions.assertTrue(Arrays.deepEquals(null, a));
    }

    @Test
    public void canHandleIncompatibleMatrixSizes() {
        double[][] a = {
            {1, 2},
            {3, 4}
        };
        double[][] b = {
            {1, 2, 3},
            {3, 4, 5}
        };
        double[][] answerAdd = Matrix.add(a, b);
        double[][] answerSub = Matrix.subtract(a, b);
        double[][] answerMlp = Matrix.subtract(a, b);
        Assertions.assertTrue(Arrays.deepEquals(null, answerAdd));
        Assertions.assertTrue(Arrays.deepEquals(null, answerSub));
        Assertions.assertTrue(Arrays.deepEquals(null, answerMlp));
    }

    @Test
    public void canHandleZeroSizedMatrix() {
        double[][] a = {
            {1, 2},
            {3, 4}
        };
        double[][] b = {};
        double[][] answerAdd = Matrix.add(a, b);
        double[][] answerSub = Matrix.subtract(a, b);
        double[][] answerMlp = Matrix.subtract(a, b);
        Assertions.assertTrue(Arrays.deepEquals(null, answerAdd));
        Assertions.assertTrue(Arrays.deepEquals(null, answerSub));
        Assertions.assertTrue(Arrays.deepEquals(null, answerMlp));
    }
}

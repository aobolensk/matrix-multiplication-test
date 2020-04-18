package task;

import java.util.Arrays;
import org.junit.Test;
import org.junit.Assert;

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
        Assert.assertTrue(Arrays.deepEquals(expected, answer));
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
        Assert.assertTrue(Arrays.deepEquals(expected, answer));
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
        Assert.assertTrue(Arrays.deepEquals(expected, answer));
    }
}

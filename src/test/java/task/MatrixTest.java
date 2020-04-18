package task;

import java.util.Arrays;
import org.junit.Test;

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
        Arrays.deepEquals(expected, answer);
    }
}

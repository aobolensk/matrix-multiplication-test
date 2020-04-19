package task;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({
        "1,+,2.23", "1,-,2.23", "1,*,2.23",
        "3.45,+,4.12", "3.45,-,4.12", "3.45,*,4.12",
        "5,*,-6", "-5,*,6", "-5,*,6",
        "0,*,2", "2,*,0", "0,*,0"
    })
    void operationsWith1x1MatricesAreTheSameAsForNumbers(String x, String op, String y) {
        double[][] a = {{Double.parseDouble(x)}};
        double[][] b = {{Double.parseDouble(y)}};
        double[][] expected = a;
        double[][] answer = null;
        switch (op) {
        case "+":
            answer = Matrix.add(a, b);
            expected[0][0] += b[0][0];
            break;
        case "-":
            answer = Matrix.subtract(a, b);
            expected[0][0] -= b[0][0];
            break;
        case "*":
            answer = Matrix.multiply(a, b);
            expected[0][0] *= b[0][0];
            break;
        default:
            Assertions.assertTrue(false);
        }
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }
}

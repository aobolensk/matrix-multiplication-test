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
        double[][] a = Matrix.read(getClass().getResource("/matrix1.txt").getFile());
        double[][] b = Matrix.read(getClass().getResource("/matrix2.txt").getFile());
        double[][] expected = Matrix.read(getClass().getResource("/matrix3.txt").getFile());
        double[][] answer = Matrix.multiply(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
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
    public void canMultiplyMatrixToItselfTransposed() {
        double[][] a = {
            {1, 4},
            {2, 5},
            {3, 6}
        };
        double[][] b = {
            {1, 2, 3},
            {4, 5, 6}
        };
        double[][] expected = {
            {17, 22, 27},
            {22, 29, 36},
            {27, 36, 45}
        };
        double[][] answer = Matrix.multiply(a, b);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    public void canMultiplyMatrixToItselfTransposed_Reversed() {
        double[][] a = {
            {1, 4},
            {2, 5},
            {3, 6}
        };
        double[][] b = {
            {1, 2, 3},
            {4, 5, 6}
        };
        double[][] expected = {
            {14, 32},
            {32, 77}
        };
        double[][] answer = Matrix.multiply(b, a);
        Assertions.assertTrue(Arrays.deepEquals(expected, answer));
    }

    public void multiplicationWithIdentityMatrixGivesMatrixItself() {
        double[][] a = {
            {1, 2, 3, 4},
            {2, 3, 4, 5},
            {.3, .7, 8.4, 13.2},
            {9.45, 4.32, 21, 45.2}
        };
        double[][] b = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        double[][] answer = Matrix.multiply(a, b);
        Assertions.assertTrue(Arrays.deepEquals(a, answer));
    }

    public void multiplicationWithIdentityMatrixGivesMatrixItself_Reversed() {
        double[][] a = {
            {1, 2, 3, 4},
            {2, 3, 4, 5},
            {.3, .7, 8.4, 13.2},
            {9.45, 4.32, 21, 45.2}
        };
        double[][] b = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        double[][] answer = Matrix.multiply(b, a);
        Assertions.assertTrue(Arrays.deepEquals(a, answer));
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

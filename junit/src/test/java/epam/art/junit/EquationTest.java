package epam.art.junit;

import org.junit.jupiter.api.Test;
import static epam.art.junit.Equation.*;
import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @Test
    void findDiscriminantTest() {
        double[] data = new double[3];
        data[0] = -1;
        data[1] = 6;
        data[2] = 7;
        double d = findDiscriminant(data);
        assertEquals (64, d);
        data[0] = 2;
        data[1] = -7;
        data[2] = 10;
        d = findDiscriminant(data);
        assertEquals (-31, d);
    }

    @Test
    void checkDiscriminantTest() {
        String two = "The equation has two roots.";
        String one = "The equation has one root.";
        String zero = "The equation has no roots.";
        String check = checkDiscriminant(5.0);
        assertEquals(two, check);
        check = checkDiscriminant(0);
        assertEquals(one, check);
        check = checkDiscriminant(-5.0);
        assertEquals(zero, check);
    }

    @Test
    void findRootsTest() {
        double[] data = {-1, 6, 7};
        double disc = 64;
        double[] extended = {-1, 7};
        double[] res = findRoots(data, disc);
        assertArrayEquals(extended, res);
        double[] data2 = {-1, 4, -4};
        disc = 0;
        res = findRoots(data2, disc);
        assertTrue(res[0] == 2);
        double[] data3 = {3, 5, 7};
        disc = -59;
        res = findRoots(data3, disc);
        assertTrue(res == null);
    }

    @Test
    void printTest() {
        String result = "x1 = -1.0, x2 = 7.0";
        double[] res = {-1, 7};
        String print = print(res, 64);
        assertEquals(result, print);
        result = "x = 0.0";
        double[] res2 = {0, 0};
        print = print(res2, 0);
        assertEquals(result, print);
    }
}
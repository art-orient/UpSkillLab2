package epam.art.junit;

import java.util.Scanner;

public class Equation {
    public static void main(String[] args) {
        double[] data = inputABC();
        double discriminant = findDiscriminant(data);
        System.out.println(checkDiscriminant(discriminant));
        double[] result = findRoots(data, discriminant);
        System.out.println(print(result, discriminant));
    }

    public static double[] inputABC() {
        Scanner scanner = new Scanner(System.in);
        double[] data = new double[3];
        System.out.println("For a given quadratic equation (ax2 + bx + c = 0) we find its roots.");
        System.out.print("Input number a (a != 0): ");
        data[0] = scanner.nextDouble();
        if (data[0] == 0) {
            System.out.println("For a = 0 the equation has no solution.");
            System.exit(0);
        }
        System.out.print("Input number b: ");
        data[1] = scanner.nextDouble();
        System.out.print("Input number c: ");
        data[2] = scanner.nextDouble();
        return data;
    }

    public static double findDiscriminant(double[] data) {
        double discriminant = data[1] * data[1] - 4 * data[0] * data[2];
        return discriminant;
    }

    public static String checkDiscriminant(double discriminant) {
        if (discriminant > 0) {
            return "The equation has two roots.";
        } else if (discriminant == 0) {
            return "The equation has one root.";
        } else {
            return "The equation has no roots.";
        }
    }

    public static double[] findRoots(double[] data, double discriminant) {
        if (discriminant < 0) {
            return null;
        }
        double[] result = new double[2];
        if (discriminant > 0) {
            result[0] = (-data[1] + Math.sqrt(discriminant)) / (2 * data[0]);
            if (result[0] == -0) {
                result[0] = 0;
            }
            result[1] = (-data[1] - Math.sqrt(discriminant)) / (2 * data[0]);
            if (result[1] == -0) {
                result[1] = 0;
            }
            return result;
        } else {
            result[0] = -data[1] / (2 * data[0]);
            if (result[0] == -0) {
                result[0] = 0;
            }
            return result;
        }
    }

    public static String print(double[] result, double discriminant) {
        if (discriminant > 0) {
            return "x1 = " + result[0] + ", x2 = " + result[1];
        } else if (discriminant == 0) {
            return "x = " + result[0];
        } else {
            return "";
        }
    }
}

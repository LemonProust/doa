import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PrimeMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******** CHECKING PRIME NUMBERS *********");
        System.out.println("---------------------------------------------");

        System.out.println("Enter the number: ");
        int num = scanner.nextInt();

        if (isPrime(num)) {
            System.out.println(STR."\{num} is a prime number!");
        } else {
            System.out.println(STR."\{num} is not a prime number!");
        }

        System.out.println("******** GENERATING PRIME MATRIX *********");
        System.out.println("---------------------------------------------");
        System.out.println("Enter the number of rows of the matrix: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number columns of the matrix: ");
        int columns = scanner.nextInt();
        generatePrimeMatrix(rows, columns);

        scanner.close();

    }

    // Prime checker
    public static boolean isPrime(int number) {
        // Handle edge cases: numbers less than 2 are not prime
        if (number < 2) {
            return false;
        }

        // 2 is the only even prime number
        if (number == 2) {
            return true;
        }

        // Even numbers greater than 2 are not prime
        if (number % 2 == 0) {
            return false;
        }

        // Here we are checking odd divisors up to square root of number
        // If number has a divisor greater than sqrt(number), it must also have a
        // corresponding divisor less than sqrt(n) - Theory
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Method responsible for generate Prime Matrix
    public static void generatePrimeMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int currentNumber = 2;
        int primesFound = 0;
        int totalPrimesNeeded = rows * cols;

        // Find enough prime numbers to fill the matrix
        while (primesFound < totalPrimesNeeded) {
            if (isPrime(currentNumber)) {
                int row = primesFound / cols;
                int col = primesFound % cols;
                matrix[row][col] = currentNumber;
                primesFound++;
            }
            currentNumber++;
        }

        // Display the matrix
        System.out.println(STR."Prime Matrix \{rows} x \{cols}: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%4d ", matrix[i][j]);
            }
            System.out.println();
        }
    }


}
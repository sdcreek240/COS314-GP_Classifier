import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter seed: ");
        long seed = scanner.nextLong();

        System.out.println("Running with seed: " + seed);

        // TODO: hook into GP training/testing
    }
}
